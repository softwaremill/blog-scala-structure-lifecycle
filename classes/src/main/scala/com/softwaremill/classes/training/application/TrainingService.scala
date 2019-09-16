package com.softwaremill.classes.training.application

import com.softwaremill.classes.training.domain.Training

import scala.concurrent.Future

class TrainingService(trainingRepository: TrainingRepository) {

  def getTrainings(): Future[List[Training]] = trainingRepository.getTrainings()
}
