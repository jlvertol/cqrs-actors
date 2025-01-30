package main

import scalasql.Table
import scalasql.core.Sc

import java.util.UUID

case class Event[T[_]](
    persistence_id: T[UUID],
    write_timestamp: T[Long],
    event_payload: T[String]
)
object Event extends Table[Event]:
  def apply(eventPayload: String): Event[Sc] = new Event(
    persistence_id = UUID.randomUUID(),
    write_timestamp = 0L,
    event_payload = eventPayload
  )
