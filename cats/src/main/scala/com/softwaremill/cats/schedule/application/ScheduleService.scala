package com.softwaremill.cats.schedule.application

import cats.effect.Sync
import cats.implicits._
import com.softwaremill.cats.diet.application.DietRepository
import com.softwaremill.cats.diet.domain.Diet
import com.softwaremill.cats.schedule.domain.Schedule
import com.softwaremill.cats.training.application.TrainingRepository
import com.softwaremill.cats.training.domain.Training

class ScheduleService[F[_]: Sync](
    scheduleRepository: ScheduleRepository[F],
    dietRepository: DietRepository[F],
    trainingRepository: TrainingRepository[F]
) {

  def getSchedules(): F[List[Schedule]] =
    for {
      diets <- dietRepository.getDiets()
      trainings <- trainingRepository.getTrainings()
      schedules <- scheduleRepository.getSchedules()
      newSchedules <- generateSchedules(diets, trainings, schedules)
    } yield newSchedules

  def generateSchedules(diets: List[Diet], trainings: List[Training], schedules: List[Schedule]): F[List[Schedule]] = ???
}
