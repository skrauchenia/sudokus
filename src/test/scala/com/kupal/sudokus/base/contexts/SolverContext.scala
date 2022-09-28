package com.kupal.sudokus.base.contexts

import com.kupal.sudokus.grid.{Grid, GridLoader}
import com.kupal.sudokus.suggesters.OptionSuggester

abstract class SolverContext(fileName: String) {

  val grid: Grid = OptionSuggester.setOptions(GridLoader.load(fileName))
}
