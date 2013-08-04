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

  val firstBlock = Array(Array(create(1, 6), create(2, 0), create(3, 0)), Array(create(10, 0), create(11, 4), create(12, 5)), Array(create(19, 0), create(20, 3), create(21, 1)))
  val lastBlock = Array(Array(create(61, 9), create(62, 0), create(63, 0)), Array(create(70, 0), create(71, 0), create(72, 0)), Array(create(79, 0), create(80, 0), create(81, 0)))
  val middleBlock = Array(Array(create(31, 1), create(32, 0), create(33, 0)), Array(create(40, 0), create(41, 5), create(42, 0)), Array(create(49, 0), create(50, 0), create(51, 2)))

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

  feature("Sudoku grid atom block") {
    scenario("Get first block 1") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get first block by first atom")
      val block = grid.getAtomBlock(create(1, 6))
      Then("Getting 6 0 0 0 4 5 0 3 1")
      assert(block.deep == firstBlock.deep)
    }

    scenario("Get first block 2") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get first block by last atom in block")
      val block = grid.getAtomBlock(create(21, 1))
      Then("Getting 6 0 0 0 4 5 0 3 1")
      assert(block.deep == firstBlock.deep)
    }

    scenario("Get first block 3") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get first block by middle atom in block")
      val block = grid.getAtomBlock(create(11, 4))
      Then("Getting 6 0 0 0 4 5 0 3 1")
      assert(block.deep == firstBlock.deep)
    }

    scenario("Get last block 1") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get last block by first block atom")
      val block = grid.getAtomBlock(create(61, 9))
      Then("Getting 9 0 0 0 0 0 0 0 0")
      assert(block.deep == lastBlock.deep)
    }

    scenario("Get last block 2") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get last block by last atom in block")
      val block = grid.getAtomBlock(create(81, 0))
      Then("Getting 9 0 0 0 0 0 0 0 0")
      assert(block.deep == lastBlock.deep)
    }

    scenario("Get last block 3") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get last block by middle atom in block")
      val block = grid.getAtomBlock(create(71, 0))
      Then("Getting 9 0 0 0 0 0 0 0 0")
      assert(block.deep == lastBlock.deep)
    }

    scenario("Get middle block 1") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get middle block by first block atom")
      val block = grid.getAtomBlock(create(31, 1))
      Then("Getting 1 0 0 0 5 0 0 0 2")
      assert(block.deep == middleBlock.deep)
    }

    scenario("Get middle block 2") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get middle block by last atom in block")
      val block = grid.getAtomBlock(create(41, 5))
      Then("Getting 1 0 0 0 5 0 0 0 2")
      assert(block.deep == middleBlock.deep)
    }

    scenario("Get middle block 3") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get middle block by middle atom in block")
      val block = grid.getAtomBlock(create(51, 2))
      Then("Getting 1 0 0 0 5 0 0 0 2")
      assert(block.deep == middleBlock.deep)
    }
  }

  feature("Sudoku grid indexed atom") {
    scenario("Get first atom") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get first atom")
      val atom = grid.getAtom(0)
      Then("Getting first atom")
      assert(atom == firstRow(0))
    }

    scenario("Get last atom") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get last atom")
      val atom = grid.getAtom(80)
      Then("Getting last atom")
      assert(atom == lastRow(8))
    }

    scenario("Get atom somewhere from the middle") {
      Given("9x9 grid")
      val grid = new SudokuGrid(grid1)
      When("Trying to get third atom from third row")
      val atom = grid.getAtom(20)
      Then("Getting first atom")
      assert(atom == thirdRow(2))
    }
  }
}
