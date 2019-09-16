package com.softwaremill.cats.training.application

import com.softwaremill.cats.training.domain.Training

class TrainingService[F[_]](trainingRepository: TrainingRepository[F]) {

  def getTrainings(): F[List[Training]] = trainingRepository.getTrainings()
}
