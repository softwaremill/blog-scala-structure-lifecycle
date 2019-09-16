package com.softwaremill.classes.training.application

import com.softwaremill.classes.training.domain.Training
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future

class TrainingRepository(database: Database) {
  def getTrainings(): Future[List[Training]] = ??? // use database
}
