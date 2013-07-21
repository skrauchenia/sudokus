package com.kupal.sudokus.process

import com.kupal.sudokus.process.model.Atom

/**
 * @author skrauchenia
 */
class SudokuGrid(val atoms: List[Atom]) {

  /**
   * Getting count of unresolved atoms
   * @return count of unresolved atoms
   */
  def unresolvedSize = (for (atom <- atoms) yield if (atom.isResolved()) 0 else 1).sum

  override def toString: String = (for (atom <- atoms) yield {
    if (atom.index % sudokuGridWidth == 0) atom + "\n"
    else atom + " "
  }).mkString
}
