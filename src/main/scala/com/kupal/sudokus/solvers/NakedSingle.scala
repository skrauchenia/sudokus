package com.kupal.sudokus.solvers

import com.kupal.sudokus.grid.Grid

/**
 * A naked single is a cell that has only one possible option
 */
object NakedSingle extends Solver {

  override def solve(idx: Int, grid: Grid): Option[Int] = ???
}
