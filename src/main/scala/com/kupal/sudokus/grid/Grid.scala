package com.kupal.sudokus.grid

case class Grid(cells: Seq[Cell]) {

  def cell(row: Int, col: Int): Cell = {
    cells(row * 9 + col)
  }

  def row(row: Int): Seq[Cell] = {
    cells.slice(row * 9, row * 9 + 9)
  }

  def col(col: Int): Seq[Cell] = {
    cells.filter(_.col == col)
  }

  def house(row: Int, col: Int): Seq[Cell] = {
    val boxRow = row / 3
    val boxCol = col / 3
    cells.filter(c => c.row / 3 == boxRow && c.col / 3 == boxCol)
  }

  def house(cell: Cell): Seq[Cell] = {
    house(cell.row, cell.col)
  }

  def row(cell: Cell): Seq[Cell] = {
    row(cell.row)
  }

  def col(cell: Cell): Seq[Cell] = {
    col(cell.col)
  }

  def getBox(row: Int): Seq[Cell] = {
    house(row, 0)
  }
}
