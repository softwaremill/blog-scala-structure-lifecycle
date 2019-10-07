package com.softwaremill.cats.schedule.application

import cats.effect.IO
import cats.implicits._
import com.softwaremill.cats.diet.application.DietRepository
import com.softwaremill.cats.diet.domain.Diet
import com.softwaremill.cats.schedule.domain.Schedule
import com.softwaremill.cats.training.application.TrainingRepository
import com.softwaremill.cats.training.domain.Training

class ScheduleService(
    scheduleRepository: ScheduleRepository,
    dietRepository: DietRepository,
    trainingRepository: TrainingRepository
) {

  def getSchedules(): IO[List[Schedule]] =
    for {
      diets <- dietRepository.getDiets()
      trainings <- trainingRepository.getTrainings()
      schedules <- scheduleRepository.getSchedules()
      newSchedules <- generateSchedules(diets, trainings, schedules)
    } yield newSchedules

  def generateSchedules(diets: List[Diet], trainings: List[Training], schedules: List[Schedule]): IO[List[Schedule]] = ???
}
