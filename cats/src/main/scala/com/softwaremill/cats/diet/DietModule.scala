package com.softwaremill.cats.diet

import cats.effect.IO
import com.softwaremill.cats.diet.application.{DietRepository, DietService}
import com.softwaremill.cats.diet.rest.DietController
import doobie.util.transactor.Transactor

class DietModule(transactor: Transactor[IO]) {

  lazy val dietRepository = new DietRepository(transactor)
  lazy val dietService = new DietService(dietRepository)
  lazy val dietController = new DietController(dietService)
}
object DietModule {

  def createSync(transactor: Transactor[IO]): IO[DietModule] = IO.delay(new DietModule(transactor))
}
