package com.softwaremill.cats.diet.application

import com.softwaremill.cats.diet.domain.Diet
import doobie.util.transactor.Transactor

class DietRepository[F[_]](transactor: Transactor[F]) {
  def getDiets(): F[List[Diet]] = ??? // use transactor
}
