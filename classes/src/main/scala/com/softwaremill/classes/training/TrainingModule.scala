package com.softwaremill.classes.training

import com.softwaremill.classes.training.application.{TrainingRepository, TrainingService}
import com.softwaremill.classes.training.rest.TrainingController
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.ExecutionContext

class TrainingModule(database: Database)(implicit executionContext: ExecutionContext) {

  lazy val trainingRepository = new TrainingRepository(database)
  lazy val trainingService = new TrainingService(trainingRepository)
  lazy val trainingController = new TrainingController(trainingService)
}
