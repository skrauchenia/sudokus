package com.kupal.sudokus.process.model

import org.scalatest.{FunSpec}

/**
 * @author skrauchenia
 */
class AtomTest extends FunSpec {

  describe("An Empty atom") {

    it("should return zero as value") {
      val empty = Atom.create(4, 0)
      assert(empty.value == 0)
    }

    it("should be indicated as unresolved") {
      val empty = Atom.createEmpty(4)
      assert(!empty.isResolved())
    }
  }

  describe("Resolved atom") {

    it("should return provided value") {
      val atom = Atom.create(5, 10)
      assert(atom.value == 10)
    }

    it("should be indicated as resolved") {
      val atom = Atom.create(5, 10)
      assert(atom.isResolved())
    }
  }
}
