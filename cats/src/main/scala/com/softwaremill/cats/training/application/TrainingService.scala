package com.softwaremill.cats.training.application

import cats.effect.IO
import com.softwaremill.cats.training.domain.Training

class TrainingService(trainingRepository: TrainingRepository) {

  def getTrainings(): IO[List[Training]] = trainingRepository.getTrainings()
}
