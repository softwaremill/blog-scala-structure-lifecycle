package com.softwaremill.cats.diet.application

import com.softwaremill.cats.diet.domain.Diet

class DietService[F[_]](dietRepository: DietRepository[F]) {

  def getDiets(): F[List[Diet]] = dietRepository.getDiets()
}
