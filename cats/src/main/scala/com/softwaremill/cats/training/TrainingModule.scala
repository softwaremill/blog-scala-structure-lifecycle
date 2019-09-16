package com.softwaremill.cats.training

import cats.effect.Sync
import com.softwaremill.cats.training.application.{TrainingRepository, TrainingService}
import com.softwaremill.cats.training.rest.TrainingController
import doobie.util.transactor.Transactor

class TrainingModule[F[_]: Sync](transactor: Transactor[F]) {

  lazy val trainingRepository = new TrainingRepository[F](transactor)
  lazy val trainingService = new TrainingService[F](trainingRepository)
  lazy val trainingController = new TrainingController[F](trainingService)
}
object TrainingModule {

  def createSync[F[_]: Sync](transactor: Transactor[F]): F[TrainingModule[F]] = Sync[F].delay(new TrainingModule[F](transactor))
}
