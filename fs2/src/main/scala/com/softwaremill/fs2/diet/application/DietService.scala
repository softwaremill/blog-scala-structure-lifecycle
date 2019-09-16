package com.softwaremill.fs2.diet.application

import com.softwaremill.fs2.diet.domain.Diet

class DietService[F[_]](dietRepository: DietRepository[F]) {

  def getDiets(): F[List[Diet]] = dietRepository.getDiets()
}
