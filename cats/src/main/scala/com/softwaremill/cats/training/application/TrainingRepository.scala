package com.softwaremill.cats.training.application

import cats.effect.IO
import com.softwaremill.cats.training.domain.Training
import doobie.util.transactor.Transactor

class TrainingRepository(transactor: Transactor[IO]) {
  def getTrainings(): IO[List[Training]] = ??? // use transactor
}
