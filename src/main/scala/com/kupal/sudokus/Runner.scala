package com.kupal.sudokus

object Runner extends App {

  val syncSolver = new SyncSolver(debug = true)

  syncSolver.solve("simple1.txt")
}
