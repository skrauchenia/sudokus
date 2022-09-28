package com.kupal.sudokus

import com.kupal.sudokus.process._
import com.typesafe.config.{ConfigFactory}

/**
 */
object Runner {

  def main(args: Array[String]) {
    if (args.isEmpty) throw new IllegalArgumentException("No pattern provided")
    val config = ConfigFactory.load("data-sets")
    val patternValue = config.getString(args(0))

    val resolvedGrid = SudokuResolver.resolve(new SudokuGrid(patternValue))

    println(s"Result = \n$resolvedGrid")
  }
}
