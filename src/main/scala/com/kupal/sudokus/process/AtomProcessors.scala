package com.kupal.sudokus.process

import com.kupal.sudokus.process.model.Atom

/**
 * @author skrauchenia
 */
object AtomProcessors {

  /**
   * Check if atom is the only unresolved atom in column
   */
  val singleInColumn = (atom: Atom, grid: SudokuGrid) => ???

  /**
   * Check if atom is the only unresolved atom in row
   */
  val singleInRow = (atom: Atom, grid: SudokuGrid) => ???

  /**
   * Check if atom is the only unresolved atom in block
   */
  val singleInBlock = (atom: Atom, grid: SudokuGrid) => ???

  val openPairs = (atom: Atom, grid: SudokuGrid) => ???

  /**
   * List of all available processors
   */
  val all: List[processor] = List(singleInColumn, singleInRow, singleInBlock, openPairs)

}
