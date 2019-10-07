package com.softwaremill.fs2.diet

import cats.effect.IO
import com.softwaremill.fs2.diet.application.{DietRepository, DietService}
import com.softwaremill.fs2.diet.rest.DietController
import doobie.util.transactor.Transactor
import fs2.Stream

class DietModule(transactor: Transactor[IO]) {

  lazy val dietRepository = new DietRepository(transactor)
  lazy val dietService = new DietService(dietRepository)
  lazy val dietController = new DietController(dietService)
}
object DietModule {

  def createSync(transactor: Transactor[IO]): IO[DietModule] = IO.delay(new DietModule(transactor))

  def create(transactor: Transactor[IO]): Stream[IO, DietModule] = Stream.eval(createSync(transactor))

}
