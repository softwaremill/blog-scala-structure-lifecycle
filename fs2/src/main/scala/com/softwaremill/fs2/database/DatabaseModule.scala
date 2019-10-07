package com.softwaremill.fs2.database

import cats.effect.{IO, Resource}
import com.softwaremill.fs2.config.Config.DatabaseConfig
import doobie.hikari.HikariTransactor

object DatabaseModule {

  def createTransactor(databaseConfig: DatabaseConfig): Resource[IO, HikariTransactor[IO]] = ???
}
