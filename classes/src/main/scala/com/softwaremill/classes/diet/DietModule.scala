package com.softwaremill.classes.diet

import com.softwaremill.classes.diet.application.{DietRepository, DietService}
import com.softwaremill.classes.diet.rest.DietController
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.ExecutionContext

class DietModule(database: Database)(implicit executionContext: ExecutionContext) {

  lazy val dietRepository = new DietRepository(database)
  lazy val dietService = new DietService(dietRepository)
  lazy val dietController = new DietController(dietService)
}
