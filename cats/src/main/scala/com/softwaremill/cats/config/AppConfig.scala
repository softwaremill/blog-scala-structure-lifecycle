package com.softwaremill.cats.config

import cats.effect.IO
import pureconfig.ConfigSource
import pureconfig.generic.auto._

object Config {

  final case class HttpServerConfig(port: Int, host: String)
  final case class DatabaseConfig()

  case class AppConfig(
      http: HttpServerConfig,
      database: DatabaseConfig
  )

  def loadSync: IO[AppConfig] =
    IO.delay(ConfigSource.default.at("app").loadOrThrow[AppConfig])
}
