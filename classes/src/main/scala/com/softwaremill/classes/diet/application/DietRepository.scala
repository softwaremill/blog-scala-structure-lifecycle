package com.softwaremill.classes.diet.application

import com.softwaremill.classes.diet.domain.Diet
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future

class DietRepository(database: Database) {
  def getDiets(): Future[List[Diet]] = ??? // use database
}
