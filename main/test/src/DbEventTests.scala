package test

import scalasql.dialects.H2Dialect.*

class DbEventTests extends munit.FunSuite with DbTestSetup:

  test("ScalaSQL works") {
    dbClient.transaction { db =>
      val result = db.runRaw[Int]("SELECT 1")
      assertEquals(result.head, 1)
    }
  }
