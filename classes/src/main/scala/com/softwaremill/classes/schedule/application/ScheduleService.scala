package com.softwaremill.classes.schedule.application

import com.softwaremill.classes.diet.application.DietRepository
import com.softwaremill.classes.diet.domain.Diet
import com.softwaremill.classes.schedule.domain.Schedule
import com.softwaremill.classes.training.application.TrainingRepository
import com.softwaremill.classes.training.domain.Training

import scala.concurrent.{ExecutionContext, Future}

class ScheduleService(
    scheduleRepository: ScheduleRepository,
    dietRepository: DietRepository,
    trainingRepository: TrainingRepository
)(implicit executionContext: ExecutionContext) {

  def getSchedules(): Future[List[Schedule]] =
    for {
      diets <- dietRepository.getDiets()
      trainings <- trainingRepository.getTrainings()
      schedules <- scheduleRepository.getSchedules()
      newSchedules <- generateSchedules(diets, trainings, schedules)
    } yield newSchedules

  def generateSchedules(diets: List[Diet], trainings: List[Training], schedules: List[Schedule]): Future[List[Schedule]] = ???
}
