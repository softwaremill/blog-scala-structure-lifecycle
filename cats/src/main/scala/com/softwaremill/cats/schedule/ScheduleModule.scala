package com.softwaremill.cats.schedule

import cats.effect.Sync
import com.softwaremill.cats.diet.application.DietRepository
import com.softwaremill.cats.schedule.application.{ScheduleRepository, ScheduleService}
import com.softwaremill.cats.schedule.rest.ScheduleController
import com.softwaremill.cats.training.application.TrainingRepository
import doobie.util.transactor.Transactor

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
}
