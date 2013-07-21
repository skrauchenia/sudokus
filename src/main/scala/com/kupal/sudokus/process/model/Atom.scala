package com.kupal.sudokus.process.model

/**
 * Represent single cell in sudoku grid
 * @author skrauchenia
 */
sealed trait Atom {

  /** atom index in sudoku grid starting from zero */
  val index: Int

  /** number related to atom from 0 till 9 */
  val value: Int

  def isResolved(): Boolean = this match {
    case Empty(_) => false
    case ResolvedAtom(_,_) => true
  }

  override def toString = value.toString
}

/**
 * Represents resolved atom
 * @param value atom value from 0 till 9
 * @param index atom index on sudoku grid
 */
case class ResolvedAtom(value: Int, index: Int) extends Atom

/**
 * Represents unresolved atom
 * @param index atom index on sudoku grid
 */
case class Empty(index: Int) extends Atom {
  val value = 0
}

/**
 * Intended to be atom's factory or builder
 */
object Atom {
  /**
   * Create empty and not resolved atom with value 0
   * @param index atom index on sudoku grid
   * @return empty atom
   */
  def createEmpty(index: Int): Atom = Empty(index)

  /**
   * Create Atom depends on value. If it zero then empty will be
   * created, if it's more then zero, than some ResolvedAtom
   * will be created
   * @param index atom index on sudoku grid
   * @param value atom value from 0 till 9
   * @return constructed Atom
   * @throws Error if value less then zero
   */
  def create(index: Int, value: Int): Atom = value match {
    case 0 => createEmpty(index)
    case _ => {
      if (value < 0) throw new Error(s"atom value can't be less then zero. Value - ${value}")
      ResolvedAtom(value, index)
    }
  }
}

