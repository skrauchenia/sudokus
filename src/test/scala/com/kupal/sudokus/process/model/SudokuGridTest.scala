package com.kupal.sudokus.process.model

import org.scalatest.{GivenWhenThen, FeatureSpec}
import com.kupal.sudokus.process.SudokuGrid

/**
 * @author skrauchenia
 */
class SudokuGridTest extends FeatureSpec with GivenWhenThen {

  feature("Sudoku grid unresolved atoms") {

    scenario("unresolvedSize invoked on resolved grid ") {
      Given("Resolved grid")
      val grid = new SudokuGrid(List(
        Atom.create(1, 3), Atom.create(2, 5), Atom.create(3, 2),
        Atom.create(4, 7), Atom.create(5, 1), Atom.create(6, 9)
      ))
      When("Invoke unresolvedSize")
      val unresolvedSize = grid.unresolvedSize
      Then("unresolvedSize should return zero")
      assert(unresolvedSize == 0)
    }

    scenario("unresolvedSize invoked on empty grid") {
      Given("Empty grid")
      val grid = new SudokuGrid(List())
      When("Invoke unresolvedSize")
      val unresolvedSize = grid.unresolvedSize
      Then("unresolvedSize should return zero")
      assert(unresolvedSize == 0)
    }

    scenario("unresolvedSize invoked on grid with various atoms") {
      Given("Ordinal grid")
      val grid = new SudokuGrid(List(
        Atom.create(1, 0), Atom.create(2, 5), Atom.create(3, 2),
        Atom.create(4, 0), Atom.create(5, 1), Atom.create(6, 9)
      ))
      When("Invoke unresolvedSize")
      val unresolvedSize = grid.unresolvedSize
      Then("unresolvedSize should return 2")
      assert(unresolvedSize == 2)
    }
  }

}
