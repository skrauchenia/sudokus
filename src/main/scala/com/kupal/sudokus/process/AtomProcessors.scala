package com.kupal.sudokus.process

import com.kupal.sudokus.process.model.Atom

/**
 * @author skrauchenia
 */
object AtomProcessors {

  /**
   * Check if atom is the only unresolved atom in column
   */
  val singleInColumn = (atom: Atom, grid: SudokuGrid) => emptyProcessor(atom, grid, "singleInColumn")

  /**
   * Check if atom is the only unresolved atom in row
   */
  val singleInRow = (atom: Atom, grid: SudokuGrid) => emptyProcessor(atom, grid, "singleInRow")

  /**
   * Check if atom is the only unresolved atom in block
   */
  val singleInBlock = (atom: Atom, grid: SudokuGrid) => emptyProcessor(atom, grid, "singleInBlock")

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

}
