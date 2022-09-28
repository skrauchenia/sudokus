package com.kupal.sudokus.grid

case class Grid(cells: Seq[Cell]) {

  val solvedCellsCount: Int = cells.count(_.solved)
  val unresolvedCells: Int = 81 - solvedCellsCount
  val solved: Boolean = solvedCellsCount == 81

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

  override def toString: String = {
    val sb = new StringBuilder
    for (i <- 0 until 9) {
      if (i % 3 == 0) {
        sb.append(" --------------------\n")
      }
      for (j <- 0 until 9) {
        if (j % 3 == 0) {
          sb.append("|")
        }
        sb.append(cell(i, j).value.getOrElse(0)).append(" ")
      }
      sb.append("|\n")
    }
    sb.append(" --------------------\n")
    sb.toString()
  }
}
