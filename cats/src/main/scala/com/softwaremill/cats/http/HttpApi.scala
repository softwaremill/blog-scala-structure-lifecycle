package com.softwaremill.cats.http

import cats.effect.IO
import cats.implicits._
import com.softwaremill.cats.diet.rest.DietController
import com.softwaremill.cats.schedule.rest.ScheduleController
import com.softwaremill.cats.training.rest.TrainingController
import org.http4s._
import org.http4s.implicits._
import org.http4s.server.Router

class HttpApi(
    dietController: DietController,
    trainingController: TrainingController,
    scheduleController: ScheduleController
) {

  def routes: HttpRoutes[IO] = dietController.routes <+> trainingController.routes <+> scheduleController.routes

  val httpApp: HttpApp[IO] = Router("/api" -> routes).orNotFound
}
