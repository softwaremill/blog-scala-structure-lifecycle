package com.softwaremill.cats.config

import cats.effect.Sync
import pureconfig.ConfigSource
import pureconfig.generic.auto._

object Config {

  final case class HttpServerConfig(port: Int, host: String)
  final case class DatabaseConfig()

  case class AppConfig(
      http: HttpServerConfig,
      database: DatabaseConfig
  )

  def loadSync[F[_]: Sync]: F[AppConfig] =
    Sync[F].delay(ConfigSource.default.at("app").loadOrThrow[AppConfig])
}
