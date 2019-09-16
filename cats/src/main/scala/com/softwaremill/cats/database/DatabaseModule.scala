package com.softwaremill.cats.database

import cats.effect.{Async, ContextShift, Resource}
import com.softwaremill.cats.config.Config.DatabaseConfig
import doobie.hikari.HikariTransactor

object DatabaseModule {

  def createTransactor[F[_]: Async: ContextShift](databaseConfig: DatabaseConfig): Resource[F, HikariTransactor[F]] = ???
}
