package com.kupal.sudokus.process

import com.kupal.sudokus.process.model.Atom

/**
 * @author skrauchenia
 */
class SudokuGrid(val atoms: Array[Atom]) {

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
    ???
  }

  def getAtomBlock(atom: Atom): Block = {
    ???
  }

  override def toString: String = (for (atom <- atoms) yield {
    if (atom.index % sudokuGridWidth == 0) atom + "\n"
    else atom + " "
  }).mkString
}
