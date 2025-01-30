package main

import castor.*
import scalasql.core.{DbClient, Sc}
import scalasql.dialects.H2Dialect.*

import java.time.Instant

class WriteActor(dbClient: DbClient, readActors: Seq[SimpleActor[Event[Sc]]])(implicit ac: Context)
    extends SimpleActor[Event[Sc]] {
  override def run(event: Event[Sc]): Unit = {
    // save the event
    val timestampedEvent = event.copy(write_timestamp = Instant.now().toEpochMilli)
    dbClient.transaction { db =>
      db.run(
        Event.insert.values(timestampedEvent)
      )
    }

    // pass it to all the read-actors
    readActors.foreach(_.run(timestampedEvent))
  }
}
