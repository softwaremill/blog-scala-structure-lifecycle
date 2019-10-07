package com.softwaremill.cats.training.rest

import cats.effect._
import cats.implicits._
import com.softwaremill.cats.training.application.TrainingService
import org.http4s._
import org.http4s.dsl.Http4sDsl

class TrainingController(trainingService: TrainingService) extends Http4sDsl[IO] {

  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "training" =>
      trainingService.getTrainings() *> Ok("TODO json converters")
  }
}
