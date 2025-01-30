package main

import castor.*
import scalasql.core.Sc

class EventPrinterActor(implicit ac: Context) extends SimpleActor[Event[Sc]] {
  override def run(event: Event[Sc]): Unit = {
    println(event.toString)
  }
}
