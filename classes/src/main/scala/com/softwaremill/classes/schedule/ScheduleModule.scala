package com.softwaremill.classes.schedule

import com.softwaremill.classes.diet.application.DietRepository
import com.softwaremill.classes.schedule.application.{ScheduleRepository, ScheduleService}
import com.softwaremill.classes.schedule.rest.ScheduleController
import com.softwaremill.classes.training.application.TrainingRepository
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.ExecutionContext

class ScheduleModule(database: Database, dietRepository: DietRepository, trainingRepository: TrainingRepository)(
    implicit executionContext: ExecutionContext
) {
  lazy val scheduleRepository = new ScheduleRepository(database)
  lazy val scheduleService = new ScheduleService(scheduleRepository, dietRepository, trainingRepository)
  lazy val scheduleController = new ScheduleController(scheduleService)
}
