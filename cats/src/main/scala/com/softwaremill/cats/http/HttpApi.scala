package com.softwaremill.cats.http

import cats.implicits._
import cats.effect.Sync
import com.softwaremill.cats.diet.rest.DietController
import com.softwaremill.cats.schedule.rest.ScheduleController
import com.softwaremill.cats.training.rest.TrainingController
import org.http4s._
import org.http4s.implicits._
import org.http4s.server.Router

class HttpApi[F[_]: Sync](
    dietController: DietController[F],
    trainingController: TrainingController[F],
    scheduleController: ScheduleController[F]
) {

  def routes: HttpRoutes[F] = dietController.routes <+> trainingController.routes <+> scheduleController.routes

  val httpApp: HttpApp[F] = Router("/api" -> routes).orNotFound
}
