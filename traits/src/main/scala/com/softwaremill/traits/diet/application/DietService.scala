package com.softwaremill.traits.diet.application

import com.softwaremill.traits.diet.domain.Diet

import scala.concurrent.Future

class DietService(dietRepository: DietRepository) {

  def getDiets(): Future[List[Diet]] = dietRepository.getDiets()
}
