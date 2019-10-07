package com.softwaremill.fs2.schedule.application
import cats.effect.IO
import cats.implicits._
import com.softwaremill.fs2.diet.application.DietRepository
import com.softwaremill.fs2.diet.domain.Diet
import com.softwaremill.fs2.schedule.domain.Schedule
import com.softwaremill.fs2.training.application.TrainingRepository
import com.softwaremill.fs2.training.domain.Training

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
