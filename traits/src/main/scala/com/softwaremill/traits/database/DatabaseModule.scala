package com.softwaremill.traits.database

import com.softwaremill.traits.config.Config
import slick.jdbc.MySQLProfile.api._

trait DatabaseModule {

  def config: Config.AppConfig
  lazy val database = createDatabase()

  def createDatabase(): Database = ???

  def closeSql(): Unit = {
    database.close()
  }
}
