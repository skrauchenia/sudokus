package com.kupal.sudokus.base.matchers

import com.kupal.sudokus.grid.Cell
import org.scalatest.matchers.{MatchResult, Matcher}

trait CellMatcher {

  def beLike(row: Int, column: Int, house: Int, solved: Boolean = false) = new CellPropertiesMatcher(row, column, house, solved)

  class CellPropertiesMatcher(row: Int, column: Int, house: Int, solved: Boolean) extends Matcher[Cell] {
    def apply(cell: Cell): MatchResult = {
      if (cell.row != row) {
        MatchResult(matches = false, s"Cell row is ${cell.row} and not $row", s"Cell row is not $row")
      } else if (cell.col != column) {
        MatchResult(matches = false, s"Cell column is ${cell.col} and not $column", s"Cell column is not $column")
      } else if (cell.house != house) {
        MatchResult(matches = false, s"Cell house is ${cell.house} and not $house", s"Cell house is not $house")
      } else if (cell.solved != solved) {
        MatchResult(matches = false, s"Cell solved is ${cell.solved} and not $solved", s"Cell solved is not $solved")
      } else {
        MatchResult(matches = true, s"Cell is like $row, $column, $house, $solved", s"Cell is not like $row, $column, $house, $solved")
      }
    }
  }
}
