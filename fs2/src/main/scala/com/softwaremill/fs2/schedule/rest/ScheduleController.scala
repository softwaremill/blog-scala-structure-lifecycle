package com.softwaremill.fs2.schedule.rest

import cats.effect._
import cats.implicits._
import com.softwaremill.fs2.schedule.application.ScheduleService
import org.http4s._
import org.http4s.dsl.Http4sDsl

class ScheduleController(scheduledService: ScheduleService) extends Http4sDsl[IO] {

  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "schedule" =>
      scheduledService.getSchedules() *> Ok("TODO json converters")
  }
}
