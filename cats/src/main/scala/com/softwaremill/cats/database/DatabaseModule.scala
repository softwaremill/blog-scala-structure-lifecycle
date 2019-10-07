package com.softwaremill.cats.database

import cats.effect.{IO, Resource}
import com.softwaremill.cats.config.Config.DatabaseConfig
import doobie.hikari.HikariTransactor

object DatabaseModule {

  def createTransactor(databaseConfig: DatabaseConfig): Resource[IO, HikariTransactor[IO]] = ???
}
