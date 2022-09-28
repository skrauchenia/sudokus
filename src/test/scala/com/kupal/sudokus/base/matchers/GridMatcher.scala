package com.kupal.sudokus.base.matchers

import com.kupal.sudokus.grid.Cell
import org.scalatest.matchers.{MatchResult, Matcher}

trait GridMatcher {

  def hasCellValues(expectedDigits: String) = new HasCellValues(expectedDigits.replaceAll("\\s", ""))

  class HasCellValues(expectedDigits: String) extends Matcher[Seq[Cell]] {
    def apply(seq: Seq[Cell]): MatchResult = {
      val actual = seq.map(_.value.getOrElse(0)).mkString
      MatchResult(
        actual == expectedDigits,
        s"Digits $actual not equal to $expectedDigits",
        s"Digits $actual equal to $expectedDigits"
      )
    }
  }
}
