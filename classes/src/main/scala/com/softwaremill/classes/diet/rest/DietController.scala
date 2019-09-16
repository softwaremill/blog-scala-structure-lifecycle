package com.softwaremill.classes.diet.rest

import akka.http.scaladsl.server.Directives._
import com.softwaremill.classes.diet.application.DietService
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._

import scala.concurrent.ExecutionContext

class DietController(dietService: DietService)(implicit executionContext: ExecutionContext) {

  def routes = pathPrefix("diet") {
    get {
      complete(dietService.getDiets().map(_ => "TODO json converters"))
    }
  }
}
