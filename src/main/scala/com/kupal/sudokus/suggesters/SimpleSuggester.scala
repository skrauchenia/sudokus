package com.kupal.sudokus.suggesters

import com.kupal.sudokus.grid.{Cell, Grid}

/**
 * uses the simple logic: checks row, cell and house of the cell to determine possible options
 */
object SimpleSuggester extends OptionSuggester {
  override def suggestOption(cell: Cell, grid: Grid): Set[Int] = {
    if (cell.solved) Set.empty
    else {
      val row = grid.row(cell.row)
      val col = grid.col(cell.col)
      val house = grid.house(cell)
      val alreadyPrecentSolutions = (row ++ col ++ house).flatMap(_.value).toSet
      (1 to 9).toSet -- alreadyPrecentSolutions
    }
  }
}
