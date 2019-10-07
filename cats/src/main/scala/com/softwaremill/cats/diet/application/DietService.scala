package com.softwaremill.cats.diet.application

import cats.effect.IO
import com.softwaremill.cats.diet.domain.Diet

class DietService(dietRepository: DietRepository) {

  def getDiets(): IO[List[Diet]] = dietRepository.getDiets()
}
