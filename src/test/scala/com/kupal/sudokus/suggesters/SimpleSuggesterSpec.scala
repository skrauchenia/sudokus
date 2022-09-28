package com.kupal.sudokus.suggesters

import com.kupal.sudokus.base.BaseSpec
import com.kupal.sudokus.base.contexts.GridContext

class SimpleSuggesterSpec extends BaseSpec {

  "SimpleSuggester.suggestOption" should {
    "suggest none if a cell already solved" in new GridContext("simple1.txt") {
      SimpleSuggester.suggestOption(grid.cell(0, 1), grid) mustBe empty
    }

    "suggest correct set of values" in new GridContext("simple1.txt") {
      SimpleSuggester.suggestOption(grid.cell(0, 0), grid) must contain only (2, 6)
      SimpleSuggester.suggestOption(grid.cell(3, 0), grid) must contain only (2, 4, 5, 6, 7)
      SimpleSuggester.suggestOption(grid.cell(1, 4), grid) must contain only 3
    }
  }
}
