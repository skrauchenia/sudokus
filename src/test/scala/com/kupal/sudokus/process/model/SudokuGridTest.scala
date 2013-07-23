package com.kupal.sudokus.process.model

import org.scalatest.{GivenWhenThen, FeatureSpec}
import com.kupal.sudokus.process.SudokuGrid
import com.kupal.sudokus.process.model.Atom._

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

  val firstRow = Array(create(1, 6), createEmpty(2), createEmpty(3), createEmpty(4), create(5, 7), createEmpty(6), createEmpty(7), create(8, 3), createEmpty(9))
  val lastRow = Array(create(73, 0), createEmpty(74), createEmpty(75), create(76, 7), create(77, 0), createEmpty(78), createEmpty(79), create(80, 0), createEmpty(81))
  val thirdRow = Array(create(19, 0), create(20, 3), create(21, 1), create(22, 9), create(23, 0), createEmpty(24), createEmpty(25), create(26, 0), createEmpty(27))

  val firstColumn = Array(create(1, 6), create(10, 0), create(19, 0), create(28, 2), create(37, 0), create(46, 0), create(55, 0), create(64, 0), create(73, 0))
  val lastColumn = Array(create(9, 0), create(18, 1), create(27, 0), create(36, 6), create(45, 0), create(54, 0), create(63, 0), create(72, 0), create(81, 0))
  val thirdColumn = Array(create(3, 0), create(12, 5), create(21, 1), create(30, 0), create(39, 4), create(48, 0), create(57, 0), create(66, 0), create(75, 0))

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

  feature("Sudoku grid atom column") {
    scenario("Get first column") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get first column by first atom")
      val column = grid.getAtomColumn(create(1, 6))
      Then("Getting 6 0 0 2 0 0 0 0 0")
      assert(column.deep == firstColumn.deep)
    }

    scenario("Get first column 2") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get first column by fours atom")
      val column = grid.getAtomColumn(createEmpty(4))
      Then("Getting 6 0 0 2 0 0 0 0 0")
      assert(column.deep == firstColumn.deep)
    }

    scenario("Get first column 3") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get first column by last atom")
      val column = grid.getAtomColumn(createEmpty(9))
      Then("Getting 6 0 0 2 0 0 0 0 0")
      assert(column.deep == firstColumn.deep)
    }

    scenario("Get last column") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get last column by first atom")
      val column = grid.getAtomColumn(createEmpty(73))
      Then("Getting 0 1 0 6 0 0 0 0 0")
      assert(column.deep == lastColumn.deep)
    }

    scenario("Get last column 2") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get last column by fours atom")
      val column = grid.getAtomColumn(create(76, 7))
      Then("Getting 0 1 0 6 0 0 0 0 0")
      assert(column.deep == lastColumn.deep)
    }

    scenario("Get last column 3") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get last column by last atom")
      val column = grid.getAtomColumn(createEmpty(81))
      Then("Getting 0 1 0 6 0 0 0 0 0")
      assert(column.deep == lastColumn.deep)
    }

    scenario("Get column from the middle") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get third column by third atom")
      val column = grid.getAtomColumn(create(21, 1))
      Then("Getting 0 5 1 0 4 0 0 0 0")
      assert(column.deep == thirdColumn.deep)
    }
  }

  feature("Sudoku grid atom row") {
    scenario("Get first row") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get first row by first atom")
      val row = grid.getAtomRow(create(1, 6))
      Then("Getting 6 0 0 0 7 0 0 3 0")
      assert(row.deep == firstRow.deep)
    }

    scenario("Get first row 2") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get first row by fours atom")
      val row = grid.getAtomRow(createEmpty(4))
      Then("Getting 6 0 0 0 7 0 0 3 0")
      assert(row.deep == firstRow.deep)
    }

    scenario("Get first row 3") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get first row by last atom")
      val row = grid.getAtomRow(createEmpty(9))
      Then("Getting 6 0 0 0 7 0 0 3 0")
      assert(row.deep == firstRow.deep)
    }

    scenario("Get last row") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get last row by first atom")
      val row = grid.getAtomRow(createEmpty(73))
      Then("Getting 0 0 0 7 0 0 0 0 0")
      assert(row.deep == lastRow.deep)
    }

    scenario("Get last row 2") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get last row by fours atom")
      val row = grid.getAtomRow(create(76, 7))
      Then("Getting 0 0 0 7 0 0 0 0 0")
      assert(row.deep == lastRow.deep)
    }

    scenario("Get last row 3") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get last row by last atom")
      val row = grid.getAtomRow(createEmpty(81))
      Then("Getting 0 0 0 7 0 0 0 0 0")
      assert(row.deep == lastRow.deep)
    }

    scenario("Get row from the middle") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get third row by third atom")
      val row = grid.getAtomRow(create(21, 1))
      Then("Getting 0 3 1 9 0 0 0 0 0")
      assert(row.deep == thirdRow.deep)
    }
  }

//  feature("Sudoku grid atom block")(pending)
}
