package com.softwaremill.traits.training

import com.softwaremill.traits.training.application.{TrainingRepository, TrainingService}
import com.softwaremill.traits.training.rest.TrainingController
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.ExecutionContext

trait TrainingModule {

  def database: Database
  implicit def executionContext: ExecutionContext
  lazy val trainingRepository = new TrainingRepository(database)
  lazy val trainingService = new TrainingService(trainingRepository)
  lazy val trainingController = new TrainingController(trainingService)
}
