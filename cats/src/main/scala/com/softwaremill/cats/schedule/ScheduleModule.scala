package com.softwaremill.cats.schedule

import cats.effect.IO
import com.softwaremill.cats.diet.application.DietRepository
import com.softwaremill.cats.schedule.application.{ScheduleRepository, ScheduleService}
import com.softwaremill.cats.schedule.rest.ScheduleController
import com.softwaremill.cats.training.application.TrainingRepository
import doobie.util.transactor.Transactor

class ScheduleModule(transactor: Transactor[IO], dietRepository: DietRepository, trainingRepository: TrainingRepository) {
  lazy val scheduleRepository = new ScheduleRepository(transactor)
  lazy val scheduleService = new ScheduleService(scheduleRepository, dietRepository, trainingRepository)
  lazy val scheduleController = new ScheduleController(scheduleService)
}
object ScheduleModule {

  def createSync(
      transactor: Transactor[IO],
      dietRepository: DietRepository,
      trainingRepository: TrainingRepository
  ): IO[ScheduleModule] = IO.delay(new ScheduleModule(transactor, dietRepository, trainingRepository))
}
