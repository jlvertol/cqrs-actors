package test

import scalasql.DbClient
import scalasql.dialects.H2Dialect.*

trait DbTestSetup:

  val dataSource = new org.h2.jdbcx.JdbcDataSource
  dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")

  val dbClient = new scalasql.DbClient.DataSource(
    dataSource,
    config = new scalasql.Config {}
  )

//  dbClient.transaction { txn =>
//    txn.updateRaw(os.read(os.Path("main/test/resources/schema.sql", os.pwd)))
//  }
