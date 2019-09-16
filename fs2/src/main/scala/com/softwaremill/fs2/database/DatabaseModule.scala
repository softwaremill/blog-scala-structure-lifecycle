package com.softwaremill.fs2.database

import cats.effect.{Async, ContextShift, Resource}
import com.softwaremill.fs2.config.Config.DatabaseConfig
import com.softwaremill.fs2.config.Config.DatabaseConfig
import doobie.hikari.HikariTransactor
import doobie.util.ExecutionContexts

object DatabaseModule {

  def createTransactor[F[_]: Async: ContextShift](databaseConfig: DatabaseConfig): Resource[F, HikariTransactor[F]] = ???
}
