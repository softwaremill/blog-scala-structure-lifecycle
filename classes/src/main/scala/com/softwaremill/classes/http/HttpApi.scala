package com.softwaremill.classes.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.softwaremill.classes.config.Config.HttpServerConfig
import com.softwaremill.classes.diet.rest.DietController
import com.softwaremill.classes.schedule.rest.ScheduleController
import com.softwaremill.classes.training.rest.TrainingController

import scala.concurrent.{ExecutionContext, Future}

class HttpApi(
    httpConfig: HttpServerConfig,
    dietController: DietController,
    trainingController: TrainingController,
    scheduleController: ScheduleController
)(implicit system: ActorSystem, materializer: ActorMaterializer, executionContext: ExecutionContext) {

  private[this] var serverBinding: Option[ServerBinding] = None

  def routes: Route = dietController.routes ~ trainingController.routes ~ scheduleController.routes

  def startHttpApi(): Future[Unit] = {
    Http().bindAndHandle(routes, httpConfig.host, httpConfig.port).map { binding =>
      serverBinding = Some(binding)
      ()
    }
  }

  def stopHttpApi(): Future[Unit] = serverBinding.map(_.unbind().map(_ => ())).getOrElse(Future.unit)

}
