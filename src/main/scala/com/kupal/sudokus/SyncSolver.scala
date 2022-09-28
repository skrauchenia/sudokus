package com.kupal.sudokus

import com.kupal.sudokus.grid.{Grid, GridLoader}
import com.kupal.sudokus.solvers.Solver
import com.kupal.sudokus.suggesters.OptionSuggester.setOptions

import scala.annotation.tailrec

case class SyncSolver(debug: Boolean = false) {

  private def log(msg: => String): Unit = if (debug) println(msg)

  def solve(fileName: String): Grid = {
    val initialGrid = GridLoader.load(fileName)
    val start = System.currentTimeMillis()
    val processedGrid = solve(initialGrid)
    val time = System.currentTimeMillis() - start
    log("")
    if (processedGrid.solved)
      log(s"Solved! Took $time ms")
    else {
      log(s"Not solved! Took $time ms Unresolved cells: ${processedGrid.unresolvedCells}")
    }
    log(processedGrid.toString)

    processedGrid
  }

  @tailrec
  private def solve(grid: Grid): Grid = {
    val gridWithOptions = setOptions(grid)
    val (updatedGrid, resolvedCount) = Solver.All.foldLeft((gridWithOptions, 0)) { case ((grid, solvedCells), solver) =>
      val updatedCells = gridWithOptions.cells.map { cell =>
        solver.solve(cell, gridWithOptions) match {
          case Some(solution) => cell.solve(solution)
          case None => cell
        }
      }

      val newGrid = Grid(updatedCells)
      val solvedAfterSolverRun = newGrid.solvedCellsCount - grid.solvedCellsCount
      if (solvedAfterSolverRun == 0) {
        log(s"${solver.name}: did not solve any cells")
        (newGrid, solvedCells)
      }
      else {
        log(s"${solver.name}: solved $solvedAfterSolverRun cells")
        val newGridWithOptions = setOptions(newGrid)
        (newGridWithOptions, solvedCells + solvedAfterSolverRun)
      }
    }

    if (resolvedCount == 0) updatedGrid
    else solve(updatedGrid)
  }
}
