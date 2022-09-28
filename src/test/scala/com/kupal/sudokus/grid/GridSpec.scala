package com.kupal.sudokus.grid

import com.kupal.sudokus.base.BaseSpec
import com.kupal.sudokus.base.matchers.GridMatcher

class GridSpec extends BaseSpec with GridMatcher {

  "Grid.cell" should {
    "return cell at given position" in {
      val grid = GridLoader.load("simple1.txt")
      grid.cell(0, 0) mustBe Cell(0)
      grid.cell(1, 8) mustBe Cell(17, 2)
    }
  }

  "Grid.row" should {
    "return row at given position" in {
      val grid = GridLoader.load("simple1.txt")
      grid.row(1) must hasCellValues("8 0 0 6 0 9 1 0 2")
    }
  }

  "Grid.col" should {
    "return col at given position" in {
      val grid = GridLoader.load("simple1.txt")
      grid.col(1) must hasCellValues("1 0 0 0 0 0 0 0 5")
    }
  }

  "Grid.house" should {
    "return house at given position" in {
      val grid = GridLoader.load("simple1.txt")
      grid.house(Cell(3)) must hasCellValues("7 4 5 6 0 9 8 0 2")
    }
  }
}
