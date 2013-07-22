package com.kupal.sudokus.process.model

import org.scalatest.{GivenWhenThen, FeatureSpec}
import com.kupal.sudokus.process.SudokuGrid

/**
 * @author skrauchenia
 */
class SudokuGridTest extends FeatureSpec with GivenWhenThen {

  val grid1 =
      "6 0 0 0 7 0 0 3 0 " +
      "0 4 5 0 0 0 6 0 1 " +
      "0 3 1 9 0 0 0 0 0 " +
      "2 0 0 1 0 0 0 0 6 " +
      "0 0 4 0 5 0 3 2 0 " +
      "0 0 0 0 0 2 0 1 0 " +
      "0 0 0 0 3 4 9 0 0 " +
      "0 5 0 6 0 0 0 0 0 " +
      "0 0 0 7 0 0 0 0 0"

  val firstRow = Array(Atom.create(1, 6), Atom.createEmpty(2), Atom.createEmpty(3), Atom.createEmpty(4), Atom.create(5, 7), Atom.createEmpty(6), Atom.createEmpty(7), Atom.create(8, 3), Atom.createEmpty(9))
  val lastRow = Array(Atom.create(1, 0), Atom.createEmpty(2), Atom.createEmpty(3), Atom.create(4, 7), Atom.create(5, 0), Atom.createEmpty(6), Atom.createEmpty(7), Atom.create(8, 0), Atom.createEmpty(9))
  val thirdRow = Array(Atom.create(1, 0), Atom.create(2, 3), Atom.create(3, 1), Atom.create(4, 9), Atom.create(5, 0), Atom.createEmpty(6), Atom.createEmpty(7), Atom.create(8, 0), Atom.createEmpty(9))

  feature("Sudoku grid unresolved atoms") {

    scenario("unresolvedSize invoked on resolved grid ") {
      Given("Resolved grid")
      val grid = new SudokuGrid("352719")
      When("Invoke unresolvedSize")
      val unresolvedSize = grid.unresolvedSize
      Then("unresolvedSize should return zero")
      assert(unresolvedSize == 0)
    }

    scenario("unresolvedSize invoked on empty grid") {
      Given("Empty grid")
      val grid = new SudokuGrid("")
      When("Invoke unresolvedSize")
      val unresolvedSize = grid.unresolvedSize
      Then("unresolvedSize should return zero")
      assert(unresolvedSize == 0)
    }

    scenario("unresolvedSize invoked on grid with various atoms") {
      Given("Ordinal grid")
      val grid = new SudokuGrid("052019")
      When("Invoke unresolvedSize")
      val unresolvedSize = grid.unresolvedSize
      Then("unresolvedSize should return 2")
      assert(unresolvedSize == 2)
    }
  }

//  feature("Sudoku grid atom column")(pending)

  feature("Sudoku grid atom row 1") {
    scenario("Get first row") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get first row by first atom")
      val row = grid.getAtomRow(Atom.create(1, 6))
      Then("Getting 6 0 0 0 7 0 0 3 0")
      assert(row == firstRow)
    }

    scenario("Get first row 2") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get first row by fours atom")
      val row = grid.getAtomRow(Atom.createEmpty(4))
      Then("Getting 6 0 0 0 7 0 0 3 0")
      assert(row == firstRow)
    }

    scenario("Get first row 3") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get first row by last atom")
      val row = grid.getAtomRow(Atom.createEmpty(9))
      Then("Getting 6 0 0 0 7 0 0 3 0")
      assert(row == firstRow)
    }

    scenario("Get last row") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get last row by first atom")
      val row = grid.getAtomRow(Atom.createEmpty(73))
      Then("Getting 0 0 0 7 0 0 0 0 0")
      assert(row == lastRow)
    }

    scenario("Get last row 2") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get last row by fours atom")
      val row = grid.getAtomRow(Atom.create(76, 7))
      Then("Getting 0 0 0 7 0 0 0 0 0")
      assert(row == lastRow)
    }

    scenario("Get last row 3") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get last row by last atom")
      val row = grid.getAtomRow(Atom.createEmpty(81))
      Then("Getting 0 0 0 7 0 0 0 0 0")
      assert(row == lastRow)
    }

    scenario("Get row from the middle") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get third row by third atom")
      val row = grid.getAtomRow(Atom.create(21, 1))
      Then("Getting 0 3 1 9 0 0 0 0 0")
      assert(row == lastRow)
    }
  }

//  feature("Sudoku grid atom block")(pending)
}
