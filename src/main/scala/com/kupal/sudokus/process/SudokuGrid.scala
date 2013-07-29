package com.kupal.sudokus.process

import com.kupal.sudokus.process.model.Atom

/**
 * @author skrauchenia
 */
class SudokuGrid(val atoms: Array[Atom]) {


  /**
   * contains atom index as key started from 1
   * and as value (blockIndex, firstAtomIndex, LastAtomIndex)
   */
  val blocksIndexesMapping: Map[Int, (Int, Int, Int)] = initBlocksIndexesMapping()

  def this(digitsSequence: Array[Int]) = this {
    var index = 0
    for (digit <- digitsSequence) yield {
      index += 1
      Atom.create(index, digit)
    }
  }

  def this(digits: String) = this(for (digit <- digits.toArray; if digit != ' ') yield digit.toString.toInt)

  /**
   * Getting count of unresolved atoms
   * @return count of unresolved atoms
   */
  def unresolvedSize = (for (atom <- atoms) yield if (atom.isResolved()) 0 else 1).sum

  def getAtomRow(atom: Atom): Row = {
    val rowIndex = (atom.index - 1) / sudokuGridWidth
    val first = rowIndex * sudokuGridWidth
    val last = first + sudokuGridWidth

    atoms.slice(first, last)
  }

  def getAtomColumn(atom: Atom): Column = {
    val first = (atom.index - 1) / sudokuGridHeight
    val last = first + (sudokuGridHeight * (sudokuGridWidth - 1))

    (for (i <- first until (last + 1 , sudokuGridWidth)) yield atoms(i)).toArray
  }

  def getAtomBlock(atom: Atom): Block = {
    val (_, first, last) = blocksIndexesMapping(atom.index)

    (for (i <- first - 1 until (last, sudokuGridWidth)) yield atoms.slice(i, i + sudokuGridBlockSize)).toArray
  }

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

  override def toString: String = (for (atom <- atoms) yield {
    if (atom.index % sudokuGridWidth == 0) atom + "\n"
    else atom + " "
  }).mkString
}
