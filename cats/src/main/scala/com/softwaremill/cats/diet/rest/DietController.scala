package com.softwaremill.cats.diet.rest

import cats.effect._
import cats.implicits._
import com.softwaremill.cats.diet.application.DietService
import org.http4s._
import org.http4s.dsl.Http4sDsl

class DietController[F[_]: Sync](dietService: DietService[F]) extends Http4sDsl[F] {

  val routes: HttpRoutes[F] = HttpRoutes.of[F] {
    case GET -> Root / "diet" =>
      dietService.getDiets().flatMap(diets => Ok("TODO json converters"))
  }
}
