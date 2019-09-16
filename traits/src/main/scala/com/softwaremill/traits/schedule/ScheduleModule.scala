package com.softwaremill.traits.schedule

import com.softwaremill.traits.diet.application.DietRepository
import com.softwaremill.traits.schedule.application.{ScheduleRepository, ScheduleService}
import com.softwaremill.traits.schedule.rest.ScheduleController
import com.softwaremill.traits.training.application.TrainingRepository
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.ExecutionContext

trait ScheduleModule {

  def database: Database
  implicit def executionContext: ExecutionContext
  def dietRepository: DietRepository
  def trainingRepository: TrainingRepository

  lazy val scheduleRepository = new ScheduleRepository(database)
  lazy val scheduleService = new ScheduleService(scheduleRepository, dietRepository, trainingRepository)
  lazy val scheduleController = new ScheduleController(scheduleService)
}
