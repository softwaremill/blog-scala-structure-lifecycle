package com.softwaremill.cats.training

import cats.effect.IO
import com.softwaremill.cats.training.application.{TrainingRepository, TrainingService}
import com.softwaremill.cats.training.rest.TrainingController
import doobie.util.transactor.Transactor

class TrainingModule(transactor: Transactor[IO]) {

  lazy val trainingRepository = new TrainingRepository(transactor)
  lazy val trainingService = new TrainingService(trainingRepository)
  lazy val trainingController = new TrainingController(trainingService)
}
object TrainingModule {

  def createSync(transactor: Transactor[IO]): IO[TrainingModule] = IO.delay(new TrainingModule(transactor))
}
