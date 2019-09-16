package com.softwaremill.fs2.training.application

import com.softwaremill.fs2.training.domain.Training
import doobie.util.transactor.Transactor

class TrainingRepository[F[_]](transactor: Transactor[F]) {
  def getTrainings(): F[List[Training]] = ??? // use transactor
}
