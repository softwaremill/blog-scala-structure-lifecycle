package com.softwaremill.cats.training.application

import com.softwaremill.cats.training.domain.Training
import doobie.util.transactor.Transactor

class TrainingRepository[F[_]](transactor: Transactor[F]) {
  def getTrainings(): F[List[Training]] = ??? // use transactor
}
