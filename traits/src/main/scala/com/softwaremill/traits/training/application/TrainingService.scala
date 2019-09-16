package com.softwaremill.traits.training.application

import com.softwaremill.traits.training.domain.Training

import scala.concurrent.Future

class TrainingService(trainingRepository: TrainingRepository) {

  def getTrainings(): Future[List[Training]] = trainingRepository.getTrainings()
}
