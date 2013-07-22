package com.kupal.sudokus

import com.kupal.sudokus.process.model.Atom

/**
 * @author skrauchenia
 */
package object process {
  /** Grid row */
  type Row = Array[Atom]

  /** Grid column */
  type Column = Array[Atom]

  /** Grid block */
  type Block = Array[Array[Atom]]

  /** sudoku field */
  type Grid = Array[Block]

  val sudokuGridBlockSize = 3
  val sudokuGridHeight = 9
  val sudokuGridWidth = 9
  val sudokuGridSpaceSize = sudokuGridHeight * sudokuGridWidth
}
