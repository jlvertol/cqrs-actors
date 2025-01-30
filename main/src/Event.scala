import java.util.UUID

case class Event[T[_]](
    ordering: T[Int],
    persistence_id: T[UUID],
    write_timestamp: T[Int],
    event_payload: T[String]
)
