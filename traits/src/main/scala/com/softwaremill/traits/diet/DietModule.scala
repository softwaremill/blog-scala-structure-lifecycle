package com.softwaremill.traits.diet

import com.softwaremill.traits.diet.application.{DietRepository, DietService}
import com.softwaremill.traits.diet.rest.DietController
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.ExecutionContext

trait DietModule {
  def database: Database
  implicit def executionContext: ExecutionContext

  lazy val dietRepository = new DietRepository(database)
  lazy val dietService = new DietService(dietRepository)
  lazy val dietController = new DietController(dietService)
}
