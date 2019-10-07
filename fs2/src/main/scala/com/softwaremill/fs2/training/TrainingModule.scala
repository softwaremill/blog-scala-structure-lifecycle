package com.softwaremill.fs2.training

import cats.effect.IO
import com.softwaremill.fs2.training.application.{TrainingRepository, TrainingService}
import com.softwaremill.fs2.training.rest.TrainingController
import doobie.util.transactor.Transactor
import fs2.Stream

class TrainingModule(transactor: Transactor[IO]) {

  lazy val trainingRepository = new TrainingRepository(transactor)
  lazy val trainingService = new TrainingService(trainingRepository)
  lazy val trainingController = new TrainingController(trainingService)
}
object TrainingModule {

  def createSync(transactor: Transactor[IO]): IO[TrainingModule] = IO.delay(new TrainingModule(transactor))
  def create(transactor: Transactor[IO]): Stream[IO, TrainingModule] = Stream.eval(createSync(transactor))
}
