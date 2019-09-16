package com.softwaremill.fs2.config

import cats.effect.Sync
import pureconfig.generic.auto._
import fs2.Stream
import pureconfig.ConfigSource

object Config {

  final case class HttpServerConfig(port: Int, host: String)
  final case class DatabaseConfig()

  case class AppConfig(
      http: HttpServerConfig,
      database: DatabaseConfig
  )

  def loadSync[F[_]: Sync]: F[AppConfig] =
    Sync[F].delay(ConfigSource.default.at("app").loadOrThrow[AppConfig])

  def load[F[_]: Sync]: Stream[F, AppConfig] = Stream.eval(loadSync)
}
