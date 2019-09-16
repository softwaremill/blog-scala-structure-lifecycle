package com.softwaremill.classes

object MainClasses extends App {

  lazy val mainModule = new MainModule()

  mainModule.start()

  sys.addShutdownHook {
    mainModule.stop()
  }
}
