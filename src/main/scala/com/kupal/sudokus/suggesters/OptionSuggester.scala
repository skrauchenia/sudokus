package com.kupal.sudokus.suggesters

import com.kupal.sudokus.grid.{Cell, Grid}

trait OptionSuggester {

  def suggestOption(cell: Cell, grid: Grid): Set[Int]
}

object OptionSuggester {

  def setOptions(grid: Grid): Grid = {
    Grid(grid.cells.map { cell =>
      val options = SimpleSuggester.suggestOption(cell, grid)
      cell.copy(options = options)
    })
  }
}
