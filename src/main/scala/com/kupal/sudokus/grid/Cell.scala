package com.kupal.sudokus.grid

case class Cell (idx: Int, value: Option[Int], options: Set[Int]) {
  require(idx >= 0 && idx < 81, "Cell index must be between 0 and 80")
  require(value.isEmpty || (value.get >= 1 && value.get <= 9), "Cell value must be between 1 and 9")

  val solved: Boolean = value.isDefined
  val row: Int = idx / 9
  val col: Int = idx % 9

  val house: Int = {
    val rowBox = row / 3
    val colBox = col / 3
    rowBox * 3 + colBox
  }

  def solve(value: Int): Cell = Cell(idx, value)
}

object Cell {
  private val AllOptions = Set(1, 2, 3, 4, 5, 6, 7, 8, 9)
  def unresolved(idx: Int): Cell = Cell(idx, None, AllOptions)
  def resolved(idx: Int, value: Int): Cell = Cell(idx, Some(value), Set.empty)

  def apply(idx: Int, value: Int = 0): Cell = value match {
    case 0 => unresolved(idx)
    case _ => resolved(idx, value)
  }
}
