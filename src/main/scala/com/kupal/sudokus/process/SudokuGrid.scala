package com.kupal.sudokus.process

import com.kupal.sudokus.process.model.Atom

/**
 * @author skrauchenia
 */
class SudokuGrid(val atoms: Array[Atom]) {

  /**
   * contains atom index as key started from 1
   * and as value (blockIndex, firstAtomNum, LastAtomNum)
   */
  val blocksIndexesMapping: Map[Int, (Int, Int, Int)] = initBlocksIndexesMapping()

  /**
   * Creates grid from sequence of digits
   * @param digitsSequence sequence of digits
   * @return
   */
  def this(digitsSequence: Array[Int]) = this {
    var index = 0
    for (digit <- digitsSequence) yield {
      index += 1
      Atom.create(index, digit)
    }
  }

  /**
   * Creates grid from string contains digits separated by space
   * @param digits e.g. "0 0 1 0 2"
   * @return grid
   */
  def this(digits: String) = this(for (digit <- digits.toArray; if digit != ' ') yield digit.toString.toInt)

  /**
   * Getting count of unresolved atoms
   * @return count of unresolved atoms
   */
  def unresolvedSize = (for (atom <- atoms) yield if (atom.isResolved()) 0 else 1).sum

  /**
   * Retrieving row of specified atom
   * @param atom atom
   * @return row
   */
  def getAtomRow(atom: Atom): Row = {
    val rowIndex = (atom.index - 1) / sudokuGridWidth
    val first = rowIndex * sudokuGridWidth
    val last = first + sudokuGridWidth

    atoms.slice(first, last)
  }

  /**
   * Retrieving column of specified atom
   * @param atom atom
   * @return column
   */
  def getAtomColumn(atom: Atom): Column = {
    val first = (atom.index - 1) / sudokuGridHeight
    val last = first + (sudokuGridHeight * (sudokuGridWidth - 1))

    (for (i <- first until (last + 1 , sudokuGridWidth)) yield atoms(i)).toArray
  }

  /**
   * Retrieving block (sudokuGridBlockSize x sudokuGridBlockSize) of specified atom
   * @param atom atom
   * @return block
   */
  def getAtomBlock(atom: Atom): Block = {
    val (_, first, last) = blocksIndexesMapping(atom.index)

    (for (i <- first - 1 until (last, sudokuGridWidth)) yield atoms.slice(i, i + sudokuGridBlockSize)).toArray
  }

  /**
   * Retrieving block (as sequence) of specified atom
   * @param atom atom
   * @return block
   */
  def getAtomBlockAsSeq(atom: Atom): BlockSeq = {
    for (blockRow <- getAtomBlock(atom); block <- blockRow) yield block
  }

  /**
   * some metadata of atom's block
   * @return (blockIndex, firstAtomNum, lastAtomNum)
   */
  def initBlocksIndexesMapping(): Map[Int, (Int, Int, Int)] = {
    val initial: Map[List[Int], (Int, Int, Int)] = Map(
      List(1, 2, 3, 10, 11, 12, 19, 20, 21) -> (0, 1, 21),
      List(4, 5, 6, 13, 14, 15, 22, 23, 24) -> (1, 4, 24),
      List(7, 8, 9, 16, 17, 18, 25, 26, 27) -> (2, 7, 27),
      List(28, 29, 30, 37, 38, 39, 46, 47, 48) -> (3, 28, 48),
      List(31, 32, 33, 40, 41, 42, 49, 50, 51) -> (4, 31, 51),
      List(34, 35, 36, 43, 44, 45, 52, 53, 54) -> (5, 34, 54),
      List(55, 56, 57, 64, 65, 66, 73, 74, 75) -> (6, 55, 75),
      List(58, 59, 60, 67, 68, 69, 76, 77, 78) -> (7, 58, 78),
      List(61, 62, 63, 70, 71, 72, 79, 80, 81) -> (8, 61, 81)
    )

    for((atoms, blockIndex) <- initial; atomIndex <- atoms) yield atomIndex -> blockIndex
  }

  /**
   * Retrieving atom by specified index
   * @param index atom index
   * @return atom
   */
  def getAtom(index: Int): Atom = atoms(index)

  /**
   * Creates new grid and replace one atom with updatedAtom
   * @param newAtom new atom
   * @return new grid
   */
  def replaceWithAtom(newAtom: Atom): SudokuGrid = {
    new SudokuGrid(
      for (atom <- this.atoms) yield {
        if (atom.index == newAtom.index) newAtom
        else atom
      }
    )
  }

  override def toString: String = (for (atom <- atoms) yield {
    if (atom.index % sudokuGridWidth == 0) atom + "\n"
    else atom + " "
  }).mkString
}
