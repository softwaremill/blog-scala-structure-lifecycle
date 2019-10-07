package com.softwaremill.fs2.training.application

import cats.effect.IO
import com.softwaremill.fs2.training.domain.Training

class TrainingService(trainingRepository: TrainingRepository) {

  def getTrainings(): IO[List[Training]] = trainingRepository.getTrainings()
}
