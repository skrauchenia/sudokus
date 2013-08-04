package com.kupal.sudokus.process

import com.kupal.sudokus.process.AtomProcessors._
import com.kupal.sudokus.process.model.Atom._
import org.scalatest.{GivenWhenThen, FeatureSpec}
import com.kupal.sudokus.process.model.Atom

/**
 * @author skrauchenia
 */
class SingleInSequenceProcessorTest extends FeatureSpec with GivenWhenThen {

  val grid1 =
      "0 1 2 3 4 5 6 8 9 " +
      "1 2 3 4 5 6 8 9 0 " +
      "2 3 0 9 4 5 6 8 1 " +
      "2 0 0 1 3 4 5 7 6 " +
      "0 0 4 0 5 0 3 2 0 " +
      "0 0 0 0 0 2 0 1 0 " +
      "0 0 0 0 3 4 9 0 0 " +
      "0 5 0 6 0 0 0 0 0 " +
      "0 0 0 7 0 0 0 0 0"

  val grid2 =
      "0 0 2 0 7 0 0 3 9 " +
      "8 4 5 0 0 0 6 0 1 " +
      "7 3 0 9 0 0 0 0 2 " +
      "2 0 3 1 0 0 0 0 6 " +
      "6 0 4 2 5 0 3 2 3 " +
      "5 0 6 3 0 2 0 1 4 " +
      "4 0 7 4 3 4 9 0 5 " +
      "3 5 8 6 0 0 0 0 7 " +
      "1 0 9 7 0 0 0 0 0"

  val grid3 =
      "0 9 8 1 7 2 0 3 0 " +
      "7 4 5 3 0 5 6 0 1 " +
      "2 3 1 9 6 8 0 0 0 " +
      "2 0 0 1 0 0 0 0 6 " +
      "0 0 4 0 5 0 3 2 0 " +
      "0 0 0 0 0 2 0 1 0 " +
      "1 2 3 0 3 4 9 5 6 " +
      "6 5 4 6 0 0 1 4 7 " +
      "0 0 7 7 0 0 2 3 0"

  val gridForRowsTest = new SudokuGrid(grid1)
  val gridForColumnsTest = new SudokuGrid(grid2)
  val gridForBlocksTest = new SudokuGrid(grid3)

  feature("Resolving atom in sequence when it is the only one, who not resolved") {
    scenario("first in row") { testSequence(singleInRow,   create(1, 0),   create(1, 7), gridForRowsTest)  }
    scenario("last in row") { testSequence(singleInRow,   create(18, 0),   create(18, 7), gridForRowsTest) }
    scenario("from middle in row") { testSequence(singleInRow,   create(21, 0),   create(21, 7), gridForRowsTest) }
    scenario("not along in row") { testSequence(singleInRow,   create(29, 0),   create(29, 0), gridForRowsTest) }

    scenario("first in column") { testSequence(singleInColumn,   create(1, 0),   create(1, 9), gridForColumnsTest) }
    scenario("last in column") { testSequence(singleInColumn,   create(81, 0),   create(81, 8), gridForColumnsTest) }
    scenario("from middle in column") { testSequence(singleInColumn,   create(21, 0),   create(21, 1), gridForColumnsTest) }
    scenario("not along in column") { testSequence(singleInColumn,   create(4, 0),   create(4, 0), gridForColumnsTest) }

    scenario("first in block") { testSequence(singleInBlock, create(1, 0), create(1, 6), gridForBlocksTest) }
    scenario("last in block") { testSequence(singleInBlock, create(81, 0), create(81, 8), gridForBlocksTest) }
    scenario("from middle in block") { testSequence(singleInBlock, create(14, 0), create(14, 4), gridForBlocksTest) }
    scenario("not along in block") { testSequence(singleInBlock, create(73, 0), create(73, 0), gridForBlocksTest) }
  }

  def testSequence(proc: processor, atom: Atom, expectedAtom: Atom, grid: SudokuGrid) = {
    val processedAtom = runProcessor(atom, grid, proc)
    assert(processedAtom == expectedAtom)
  }

}
