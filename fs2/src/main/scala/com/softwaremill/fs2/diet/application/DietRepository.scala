package com.softwaremill.fs2.diet.application

import com.softwaremill.fs2.diet.domain.Diet
import doobie.util.transactor.Transactor

class DietRepository[F[_]](transactor: Transactor[F]) {
  def getDiets(): F[List[Diet]] = ??? // use transactor
}
