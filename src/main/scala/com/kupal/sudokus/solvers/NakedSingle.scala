package com.kupal.sudokus.solvers

import com.kupal.sudokus.grid.{Cell, Grid}

/**
 * A naked single is a cell that has only one possible option
 */
object NakedSingle extends Solver {

  override def solve(cell: Cell, grid: Grid): Option[Int] =
    if (cell.options.size == 1) cell.options.headOption else None

  override def name: String = "Naked Single"
}
