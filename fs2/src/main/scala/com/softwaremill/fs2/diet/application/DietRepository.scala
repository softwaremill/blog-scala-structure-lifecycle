package com.softwaremill.fs2.diet.application

import cats.effect.IO
import com.softwaremill.fs2.diet.domain.Diet
import doobie.util.transactor.Transactor

class DietRepository(transactor: Transactor[IO]) {
  def getDiets(): IO[List[Diet]] = ??? // use transactor
}
