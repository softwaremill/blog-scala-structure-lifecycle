package com.softwaremill.traits.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.softwaremill.traits.config.Config

import scala.concurrent.{ExecutionContext, Future}

trait HttpApi {

  implicit def system: ActorSystem
  implicit def materializer: ActorMaterializer
  implicit def executionContext: ExecutionContext

  def config: Config.AppConfig

  private[this] var serverBinding: Option[ServerBinding] = None

  def routes: Route

  def startHttpApi(): Future[Unit] = {
    Http().bindAndHandle(routes, config.http.host, config.http.port).map { binding =>
      serverBinding = Some(binding)
      ()
    }
  }

  def stopHttpApi(): Future[Unit] = serverBinding.map(_.unbind().map(_ => ())).getOrElse(Future.unit)

}
