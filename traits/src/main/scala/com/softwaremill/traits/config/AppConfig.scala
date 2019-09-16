package com.softwaremill.traits.config

import pureconfig.ConfigSource
import pureconfig.generic.auto._

object Config {

  final case class HttpServerConfig(port: Int, host: String)
  final case class DatabaseConfig()

  case class AppConfig(
      http: HttpServerConfig,
      database: DatabaseConfig
  )

  def load(): AppConfig = ConfigSource.default.at("app").loadOrThrow[AppConfig]
}
