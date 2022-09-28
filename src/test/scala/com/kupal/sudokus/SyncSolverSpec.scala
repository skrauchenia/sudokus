package com.kupal.sudokus

import com.kupal.sudokus.base.BaseSpec

class SyncSolverSpec extends BaseSpec {

  val solver: SyncSolver = SyncSolver()

  "SyncSolver" must {
    "Solve simple grid" in {
      solver.solve("simple1.txt").solved mustBe true
    }
  }
}
