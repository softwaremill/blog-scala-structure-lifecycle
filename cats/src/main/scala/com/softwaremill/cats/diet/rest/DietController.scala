package com.softwaremill.cats.diet.rest

import cats.effect._
import cats.implicits._
import com.softwaremill.cats.diet.application.DietService
import org.http4s._
import org.http4s.dsl.Http4sDsl

class DietController(dietService: DietService) extends Http4sDsl[IO] {

  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "diet" =>
      dietService.getDiets().flatMap(diets => Ok("TODO json converters"))
  }
}
