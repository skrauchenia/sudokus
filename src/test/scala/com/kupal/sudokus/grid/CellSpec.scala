package com.kupal.sudokus.grid

import com.kupal.sudokus.base.BaseSpec
import com.kupal.sudokus.base.matchers.CellMatcher

class CellSpec extends BaseSpec with CellMatcher {

  "A Cell" should {
    "have correct coordinates" in {
      Cell(0) must beLike(0, 0, 0)
      Cell(1) must beLike(0, 1, 0)
      Cell(8) must beLike(0, 8, 2)
      Cell(9) must beLike(1, 0, 0)
      Cell(45) must beLike(5, 0, 3)
      Cell(80) must beLike(8, 8, 8)
    }

    "have correct initial options" in {
      Cell(0).options mustBe Set(1, 2, 3, 4, 5, 6, 7, 8, 9)
      Cell(0, 1).options mustBe empty
    }
  }

}
