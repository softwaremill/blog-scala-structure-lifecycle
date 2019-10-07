package com.softwaremill.fs2.diet.application

import cats.effect.IO
import com.softwaremill.fs2.diet.domain.Diet

class DietService(dietRepository: DietRepository) {

  def getDiets(): IO[List[Diet]] = dietRepository.getDiets()
}
