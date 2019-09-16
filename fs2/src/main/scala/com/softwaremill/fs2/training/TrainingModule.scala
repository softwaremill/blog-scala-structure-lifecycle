package com.softwaremill.fs2.training

import cats.effect.Sync
import com.softwaremill.fs2.training.application.{TrainingRepository, TrainingService}
import com.softwaremill.fs2.training.rest.TrainingController
import doobie.util.transactor.Transactor
import fs2.Stream

class TrainingModule[F[_]: Sync](transactor: Transactor[F]) {

  lazy val trainingRepository = new TrainingRepository[F](transactor)
  lazy val trainingService = new TrainingService[F](trainingRepository)
  lazy val trainingController = new TrainingController[F](trainingService)
}
object TrainingModule {

  def createSync[F[_]: Sync](transactor: Transactor[F]): F[TrainingModule[F]] = Sync[F].delay(new TrainingModule[F](transactor))
  def create[F[_]: Sync](transactor: Transactor[F]): Stream[F, TrainingModule[F]] = Stream.eval(createSync(transactor))
}
