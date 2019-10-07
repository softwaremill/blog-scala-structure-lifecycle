package com.softwaremill.fs2.schedule

import cats.effect.IO
import com.softwaremill.fs2.diet.application.DietRepository
import com.softwaremill.fs2.schedule.application.{ScheduleRepository, ScheduleService}
import com.softwaremill.fs2.schedule.rest.ScheduleController
import com.softwaremill.fs2.training.application.TrainingRepository
import doobie.util.transactor.Transactor
import fs2.Stream

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

  def create(
      transactor: Transactor[IO],
      dietRepository: DietRepository,
      trainingRepository: TrainingRepository
  ): Stream[IO, ScheduleModule] = Stream.eval(createSync(transactor, dietRepository, trainingRepository))
}
