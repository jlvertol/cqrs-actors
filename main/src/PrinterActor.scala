package main

import castor.*

class PrinterActor(implicit ac: Context) extends SimpleActor[String] {
  override def run(str: String): Unit = {
    println(str)
  }
}
