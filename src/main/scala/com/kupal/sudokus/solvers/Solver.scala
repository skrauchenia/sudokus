package com.kupal.sudokus.solvers

import com.kupal.sudokus.grid.Grid

trait Solver {

  def solve(idx: Int, grid: Grid): Option[Int]

}
