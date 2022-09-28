package com.kupal.sudokus.solvers

import com.kupal.sudokus.base.BaseSpec
import com.kupal.sudokus.base.contexts.SolverContext

class NakedSingleSpec extends BaseSpec {

  "NakedSingle.solve" must {
    "solve cell" in new SolverContext("simple1.txt") {
      NakedSingle.solve(grid.cell(1, 4), grid) mustBe Some(3)
    }

    "doesn't unsolvable cell" in new SolverContext("simple1.txt") {
      NakedSingle.solve(grid.cell(1, 2), grid) mustBe None
    }
  }
}
