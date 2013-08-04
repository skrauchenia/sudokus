package com.kupal.sudokus.process

import com.kupal.sudokus.process.model.Atom

/**
 * @author skrauchenia
 */
object AtomProcessors {

  def singleInSequence(atom: Atom, sequence: Array[Atom]): Atom = {
    val unresolvedCount: Int = sequence.filter(!_.isResolved()).length
    if (unresolvedCount > 1) return atom

    def processSequence(leftPossibleSolutions: Seq[Int], seq: Array[Atom]): Atom = leftPossibleSolutions match {
      case lastSolution :: Nil => Atom.create(atom.index, lastSolution)
      case head :: tail => processSequence(leftPossibleSolutions diff List(seq.head.value), seq.tail)
    }
    processSequence(List(1, 2, 3, 4, 5, 6, 7, 8, 9), sequence)
  }

  /**
   * Check if atom is the only unresolved atom in column
   */
  val singleInColumn = (atom: Atom, grid: SudokuGrid) => {
    val column = grid.getAtomColumn(atom)
    singleInSequence(atom, column)
  }

  /**
   * Check if atom is the only unresolved atom in row
   */
  val singleInRow = (atom: Atom, grid: SudokuGrid) => {
    val row = grid.getAtomRow(atom)
    singleInSequence(atom, row)
  }

  /**
   * Check if atom is the only unresolved atom in block
   */
  val singleInBlock = (atom: Atom, grid: SudokuGrid) => {
    val block = grid.getAtomBlockAsSeq(atom)
    singleInSequence(atom, block)
  }

  val openPairs = (atom: Atom, grid: SudokuGrid) => emptyProcessor(atom, grid, "openPairs")

  def emptyProcessor(atom: Atom, grid: SudokuGrid, procName: String): Atom = {
    println(s"$procName checking atom ${atom.index}")
    atom
  }

  /**
   * List of all available processors
   */
  val all: List[processor] = List(singleInColumn, singleInRow, singleInBlock, openPairs)

  def processAtom(atom: Atom, grid: SudokuGrid): Atom = {
    def process(processors: List[processor]): Atom = processors match {
      case head :: Nil => head(atom, grid)
      case head :: tail => {
        val updatedAtom = head(atom, grid)
        if (updatedAtom.isResolved()) updatedAtom
        else process(tail)
      }
    }

    process(all)
  }

  def runProcessor(atom: Atom, grid: SudokuGrid, processor: processor) = {
    if (atom.isResolved()) atom
    else processor(atom, grid)
  }

}
