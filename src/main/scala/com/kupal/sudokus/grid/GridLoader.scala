package com.kupal.sudokus.grid

object GridLoader {

  def load(fileName: String): Grid = {
    val src = scala.io.Source.fromInputStream(getClass.getClassLoader.getResourceAsStream(fileName))
    val cells = src.getLines.flatMap(_.split("\\s+").map(_.toInt)).zipWithIndex.map {
      case (value, index) => Cell(index, value)
    }.toList

    Grid(cells)
  }
}
