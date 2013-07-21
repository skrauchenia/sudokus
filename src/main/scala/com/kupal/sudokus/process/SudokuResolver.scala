package com.kupal.sudokus.process

import com.kupal.sudokus.process.model.Atom

/**
 * @author skrauchenia
 */
class SudokuResolver(val digitsSequence: List[Int]) {

  def this(digits: String) = this(for (digit <- digits.toList; if digit != ' ') yield digit.toString.toInt)

  def createColumns() = List(List(1))

  def createRows() = List(List(1))

  def createGrid() = List(List(List(1)))

  def resolve(): SudokuGrid = {
    var index = 0
    val atoms = for (digit <- digitsSequence) yield {
      index += 1
      Atom.create(index, digit)
    }

    new SudokuGrid(atoms)
  }
}
