package com.kupal.sudokus.base.contexts

import com.kupal.sudokus.grid.{Grid, GridLoader}

abstract class GridContext(fileName: String) {

  val grid: Grid = GridLoader.load(fileName)
}
