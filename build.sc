import $ivy.`com.lihaoyi::mill-contrib-scalapblib:`
import $ivy.`com.lihaoyi::mill-contrib-docker:$MILL_VERSION`
import mill._
import scalalib._
import scalalib.scalafmt.ScalafmtModule

// Package versions
private object V {
  val Scala = "3.6.3"
  val ScalaToolkit = "0.4.0"
  val TypesafeConfig = "1.4.1"
  val ScalaSql = "0.1.15"
  val Munit = "0.7.29"
  val Castor = "0.3.0"
  val H2 = "2.3.232"
}

object main extends ScalaModule with ScalafmtModule {
  def scalaVersion = V.Scala

  def ivyDeps = Agg(
    ivy"com.lihaoyi::castor:${V.Castor}",
    ivy"com.typesafe:config:${V.TypesafeConfig}",
    ivy"org.scala-lang::toolkit:${V.ScalaToolkit}",
    ivy"com.lihaoyi::scalasql:${V.ScalaSql}",
    ivy"com.h2database:h2:${V.H2}"
  )
  object test extends ScalaTests {
    def testFramework = "munit.Framework"
    def ivyDeps = Agg(
      ivy"org.scalameta::munit:${V.Munit}"
    )
  }
}