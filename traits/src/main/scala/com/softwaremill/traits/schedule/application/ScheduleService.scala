package com.softwaremill.traits.schedule.application

import com.softwaremill.traits.diet.application.DietRepository
import com.softwaremill.traits.diet.domain.Diet
import com.softwaremill.traits.schedule.domain.Schedule
import com.softwaremill.traits.training.application.TrainingRepository
import com.softwaremill.traits.training.domain.Training

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
