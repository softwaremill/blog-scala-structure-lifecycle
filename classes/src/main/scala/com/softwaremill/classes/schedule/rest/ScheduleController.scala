package com.softwaremill.classes.schedule.rest

import com.softwaremill.classes.schedule.application.ScheduleService
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._
import akka.http.scaladsl.server.Directives._

import scala.concurrent.ExecutionContext

class ScheduleController(scheduledService: ScheduleService)(implicit executionContext: ExecutionContext) {

  def routes = pathPrefix("schedule") {
    get {
      complete(scheduledService.getSchedules().map(_ => "TODO json converters"))
    }
  }
}
