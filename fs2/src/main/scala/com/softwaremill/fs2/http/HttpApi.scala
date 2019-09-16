package com.softwaremill.fs2.http

import cats.implicits._
import cats.effect.Sync
import com.softwaremill.fs2.diet.rest.DietController
import com.softwaremill.fs2.schedule.rest.ScheduleController
import com.softwaremill.fs2.training.rest.TrainingController
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
