package com.softwaremill.traits

import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, Materializer}

import scala.concurrent.Await
import scala.util.Try
import scala.concurrent.duration._

object MainTraits extends App with MainModule {

  override implicit lazy val system: ActorSystem = ActorSystem("traits")

  override implicit lazy val materializer: ActorMaterializer = ActorMaterializer()(system)

  override implicit def executionContext = system.dispatcher

  start()

  sys.addShutdownHook {
    Try(Await.ready(system.terminate(), 15.seconds))
    stop()
  }
}
