package com.softwaremill.traits.training.application

import com.softwaremill.traits.training.domain.Training
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future

class TrainingRepository(database: Database) {
  def getTrainings(): Future[List[Training]] = ??? // use database
}
