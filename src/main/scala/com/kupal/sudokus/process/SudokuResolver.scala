package com.kupal.sudokus.process

import com.kupal.sudokus.process.AtomProcessors._

/**
 * @author skrauchenia
 */

object SudokuResolver {

  val lastGridIndex = (sudokuGridHeight * sudokuGridWidth) - 1

  def incrementIndex(i: Int) = {
    if (i == lastGridIndex) 0
    else i + 1
  }

  def resolve(grid: SudokuGrid): SudokuGrid = {

    def resolveAcc(index: Int, updatedGrid: SudokuGrid): SudokuGrid = {
      if (grid.unresolvedSize > 0) {
        println(s"processing atom $index")
        val nextIndex = incrementIndex(index)
        val atom = updatedGrid.getAtom(index)
        var newGrid = updatedGrid
        if (!atom.isResolved()) {
          val updatedAtom = processAtom(atom, grid)
          if (updatedAtom.isResolved()) {
            newGrid = grid.replaceWithAtom(updatedAtom)
          }
        }
        resolveAcc(nextIndex, newGrid)
      } else updatedGrid
    }
    resolveAcc(0, grid)
  }
}
