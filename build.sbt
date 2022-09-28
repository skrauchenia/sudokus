import sbt.Keys.libraryDependencies

ThisBuild / scalaVersion     := "2.13.0"
ThisBuild / version          := "0.9"
ThisBuild / organization     := "com.kupal"
ThisBuild / organizationName := "kupal"

lazy val root = (project in file("."))
  .settings(
    name := "sudokus",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test,
    libraryDependencies += "com.typesafe" % "config" % "1.0.2"
  )

