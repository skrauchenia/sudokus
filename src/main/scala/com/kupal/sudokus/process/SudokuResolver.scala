package com.kupal.sudokus.process

/**
 * @author skrauchenia
 */
class SudokuResolver(val grid: SudokuGrid) {

  def createColumns() = List(List(1))

  def createRows() = List(List(1))

  def createGrid() = List(List(List(1)))

  def resolve(): SudokuGrid = {
    grid
  }
}
