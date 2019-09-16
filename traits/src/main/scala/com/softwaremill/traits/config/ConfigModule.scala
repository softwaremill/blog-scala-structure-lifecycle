package com.softwaremill.traits.config

trait ConfigModule {
  lazy val config: Config.AppConfig = Config.load()
}
