package com.kupal.sudokus.process

import com.kupal.sudokus.process.model.Atom._
import org.scalatest.{GivenWhenThen, FeatureSpec}
import com.kupal.sudokus.process.model.Atom

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

  val grid = new SudokuGrid(grid1)

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

    def testUnresolvedSize(grid: SudokuGrid, expectedSize: Int) = {
      val unresolvedSize = grid.unresolvedSize
      assert(unresolvedSize == expectedSize)
    }

    scenario("unresolvedSize invoked on resolved grid should return zero") {
      testUnresolvedSize(new SudokuGrid("352719"), 0)
    }

    scenario("unresolvedSize invoked on empty grid should return zero") {
      testUnresolvedSize(new SudokuGrid(""), 0)
    }

    scenario("unresolvedSize invoked on grid with various atoms should return 2") {
      testUnresolvedSize(new SudokuGrid("052019"), 2)
    }
  }

  def determineStructure(expectedStructure: Array[_], atom: Atom, structureDeterminer: (Atom) => Array[_]) = {
    val structure = structureDeterminer(atom)
    assert(structure sameElements expectedStructure)
  }

  feature("Determine Sudoku grid column by atom") {
    scenario("get first column by first atom") { determineStructure(firstColumn, create(1, 6), grid.getAtomColumn) }
    scenario("get first column by fours atom") { determineStructure(firstColumn, create(28, 2), grid.getAtomColumn) }
    scenario("get first column by last atom") { determineStructure(firstColumn, create(73, 0), grid.getAtomColumn) }

    scenario("get last column by first atom") { determineStructure(lastColumn, create(9, 0), grid.getAtomColumn) }
    scenario("get last column by fours atom") { determineStructure(lastColumn, create(36, 6), grid.getAtomColumn) }
    scenario("get last column by last atom") { determineStructure(lastColumn, create(81, 0), grid.getAtomColumn) }

    scenario("get third column by third atom") { determineStructure(thirdColumn, create(3, 0), grid.getAtomColumn) }
    scenario("get third column by fourth atom") { determineStructure(thirdColumn, create(30, 0), grid.getAtomColumn) }
    scenario("get third column by last atom") { determineStructure(thirdColumn, create(75, 0), grid.getAtomColumn) }
  }

  feature("Determine Sudoku grid row by atom") {
    scenario("get first row by first atom") { determineStructure(firstRow, create(1, 6), grid.getAtomRow) }
    scenario("get first row by fours atom") { determineStructure(firstRow, create(4, 0), grid.getAtomRow) }
    scenario("get first row by last atom") { determineStructure(firstRow, create(9, 0), grid.getAtomRow) }

    scenario("get last row by first atom") { determineStructure(lastRow, create(73, 0), grid.getAtomRow) }
    scenario("get last row by fours atom") { determineStructure(lastRow, create(76, 7), grid.getAtomRow) }
    scenario("get last row by last atom") { determineStructure(lastRow, create(81, 0), grid.getAtomRow) }

    scenario("get third row by third atom") { determineStructure(thirdRow, create(21, 1), grid.getAtomRow) }
  }

  feature("Determine Sudoku grid block by atom") {
    scenario("get first block by first atom") { determineStructure(firstBlock, create(1, 6), grid.getAtomBlock) }
    scenario("get first block by last atom in block") { determineStructure(firstBlock, create(21, 1), grid.getAtomBlock) }
    scenario("get first block by middle atom in block") { determineStructure(firstBlock, create(11, 4), grid.getAtomBlock) }

    scenario("get last block by first block atom") { determineStructure(lastBlock, create(61, 9), grid.getAtomBlock) }
    scenario("get last block by last atom in block") { determineStructure(lastBlock, create(81, 0), grid.getAtomBlock) }
    scenario("get last block by middle atom in block") { determineStructure(lastBlock, create(71, 0), grid.getAtomBlock) }

    scenario("get middle block by first block atom") { determineStructure(middleBlock, create(31, 1), grid.getAtomBlock) }
    scenario("get middle block by last atom in block") { determineStructure(middleBlock, create(41, 5), grid.getAtomBlock) }
    scenario("get middle block by middle atom in block") { determineStructure(middleBlock, create(51, 2), grid.getAtomBlock) }
  }

  feature("Getting atom by index from sudoku grid") {
    def testGetAtomByIndex(index: Int, expectedAtom: Atom) = {
      val atom = grid.getAtom(index)
      assert(atom == expectedAtom)
    }

    scenario("Get first atom") { testGetAtomByIndex(0, firstRow(0)) }
    scenario("Get last atom") { testGetAtomByIndex(80, lastRow(8)) }
    scenario("Get atom somewhere from the middle") { testGetAtomByIndex(20, thirdRow(2)) }
  }
}
