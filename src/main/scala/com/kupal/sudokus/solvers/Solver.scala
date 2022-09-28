package com.kupal.sudokus.solvers

import com.kupal.sudokus.grid.{Cell, Grid}

trait Solver {

  def name: String
  def solve(cell: Cell, grid: Grid): Option[Int]

}

object Solver {

  val All: Seq[Solver] = Seq(NakedSingle)
}
