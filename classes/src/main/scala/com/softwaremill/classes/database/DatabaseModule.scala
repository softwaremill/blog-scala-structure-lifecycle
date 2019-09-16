package com.softwaremill.classes.database

import com.softwaremill.classes.config.Config.DatabaseConfig
import slick.jdbc.MySQLProfile.api._

class DatabaseModule(databaseConfig: DatabaseConfig) {

  lazy val database = createDatabase()

  def createDatabase(): Database = ???

  def closeSql(): Unit = {
    database.close()
  }
}
