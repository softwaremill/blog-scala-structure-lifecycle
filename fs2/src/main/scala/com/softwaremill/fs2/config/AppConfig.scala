package com.softwaremill.fs2.config

import cats.effect.IO
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

  def loadSync: IO[AppConfig] =
    IO.delay(ConfigSource.default.at("app").loadOrThrow[AppConfig])

  def load: Stream[IO, AppConfig] = Stream.eval(loadSync)
}
