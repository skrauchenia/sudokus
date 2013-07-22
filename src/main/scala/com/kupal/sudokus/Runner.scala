package com.kupal.sudokus

import com.kupal.sudokus.process._
import com.typesafe.config.{ConfigFactory}

/**
 */
object Runner {

  def main(args: Array[String]) {
    if (args.size == 0) throw new IllegalArgumentException("No pattern provided")
    val config = ConfigFactory.load("data-sets")
    val patternValue = config.getString(args(0))

    val resolver = new SudokuResolver(new SudokuGrid(patternValue))
    val grid: SudokuGrid = resolver.resolve()

    println(s"Result = \n${grid}")
  }
}
