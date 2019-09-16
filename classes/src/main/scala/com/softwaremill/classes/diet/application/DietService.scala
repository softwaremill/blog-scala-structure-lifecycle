package com.softwaremill.classes.diet.application

import com.softwaremill.classes.diet.domain.Diet

import scala.concurrent.Future

class DietService(dietRepository: DietRepository) {

  def getDiets(): Future[List[Diet]] = dietRepository.getDiets()
}
