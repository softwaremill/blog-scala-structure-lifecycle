package com.softwaremill.fs2.schedule

import cats.effect.Sync
import com.softwaremill.fs2.diet.application.DietRepository
import com.softwaremill.fs2.schedule.application.{ScheduleRepository, ScheduleService}
import com.softwaremill.fs2.schedule.rest.ScheduleController
import com.softwaremill.fs2.training.application.TrainingRepository
import doobie.util.transactor.Transactor
import fs2.Stream

class ScheduleModule[F[_]: Sync](transactor: Transactor[F], dietRepository: DietRepository[F], trainingRepository: TrainingRepository[F]) {
  lazy val scheduleRepository = new ScheduleRepository[F](transactor)
  lazy val scheduleService = new ScheduleService[F](scheduleRepository, dietRepository, trainingRepository)
  lazy val scheduleController = new ScheduleController[F](scheduleService)
}
object ScheduleModule {

  def createSync[F[_]: Sync](
      transactor: Transactor[F],
      dietRepository: DietRepository[F],
      trainingRepository: TrainingRepository[F]
  ): F[ScheduleModule[F]] = Sync[F].delay(new ScheduleModule[F](transactor, dietRepository, trainingRepository))

  def create[F[_]: Sync](
      transactor: Transactor[F],
      dietRepository: DietRepository[F],
      trainingRepository: TrainingRepository[F]
  ): Stream[F, ScheduleModule[F]] = Stream.eval(createSync(transactor, dietRepository, trainingRepository))
}
