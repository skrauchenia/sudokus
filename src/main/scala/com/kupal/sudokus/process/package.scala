package com.kupal.sudokus

/**
 * @author skrauchenia
 */
package object process {
  /** Grid row */
  type Row = List[Int]

  /** Grid column */
  type Column = List[Int]

  /** Grid block */
  type Block = List[List[Int]]

  /** sudoku field */
  type Grid = List[Block]

  val sudokuGridBlockSize = 3
  val sudokuGridHeight = 9
  val sudokuGridWidth = 9
  val sudokuGridSpaceSize = sudokuGridHeight * sudokuGridWidth
}
