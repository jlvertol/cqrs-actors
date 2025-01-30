package test

import main.*
import scalasql.dialects.H2Dialect.*
import castor.Context.Simple.global

import java.io.{ByteArrayOutputStream, PrintStream}

class ActorTests extends munit.FunSuite with DbTestSetup:

  test("PrinterActor works") {
    val outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream))
    val pa      = new PrinterActor
    val strings = Seq("boop", "doop", "snoop")
    strings.foreach(s => pa.run(s))

    val expectedOutput = strings.mkString("\n") + "\n"
    assertEquals(expectedOutput, outputStream.toString())
  }

  test("WriteActor works") {
    val epa = new EventPrinterActor
    val wa  = new WriteActor(dbClient, Seq(epa))

    val event1 = Event("first")
    val event2 = Event("second")

    wa.run(event1)
    wa.run(event2)

    dbClient.transaction { db =>
      val events = db.run(Event.select)

      assertEquals(
        events.map(e => (e.persistence_id, e.event_payload)),
        Seq(event1, event2).map(e => (e.persistence_id, e.event_payload))
      )
    }
  }
