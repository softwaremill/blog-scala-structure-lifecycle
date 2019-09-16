package com.softwaremill.cats.training.rest

import cats.effect._
import cats.implicits._
import com.softwaremill.cats.training.application.TrainingService
import org.http4s._
import org.http4s.dsl.Http4sDsl

class TrainingController[F[_]: Sync](trainingService: TrainingService[F]) extends Http4sDsl[F] {

  val routes: HttpRoutes[F] = HttpRoutes.of[F] {
    case GET -> Root / "training" =>
      trainingService.getTrainings() *> Ok("TODO json converters")
  }
}
