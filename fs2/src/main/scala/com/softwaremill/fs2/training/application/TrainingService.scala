package com.softwaremill.fs2.training.application

import com.softwaremill.fs2.training.domain.Training

class TrainingService[F[_]](trainingRepository: TrainingRepository[F]) {

  def getTrainings(): F[List[Training]] = trainingRepository.getTrainings()
}
