package com.softwaremill.traits.training.rest

import com.softwaremill.traits.training.application.TrainingService
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._
import akka.http.scaladsl.server.Directives._

import scala.concurrent.ExecutionContext

class TrainingController(trainingService: TrainingService)(implicit executionContext: ExecutionContext) {

  def routes = pathPrefix("training") {
    get {
      complete(trainingService.getTrainings().map(_ => "TODO json converters"))
    }
  }
}
