package com.softwaremill.fs2

import cats.effect._
import com.softwaremill.fs2.config.Config
import com.softwaremill.fs2.database.DatabaseModule
import com.softwaremill.fs2.diet.DietModule
import com.softwaremill.fs2.http.HttpApi
import com.softwaremill.fs2.schedule.ScheduleModule
import com.softwaremill.fs2.training.TrainingModule
import fs2.Stream
import org.http4s.server.blaze.BlazeServerBuilder

class MainModule {

  def start()(implicit concurrentEffect: ConcurrentEffect[IO], timer: Timer[IO], contextShift: ContextShift[IO]): Stream[IO, Unit] = {
    for {
      config <- Config.load
      transactorResource = DatabaseModule.createTransactor(config.database)
      transactor <- Stream.resource(transactorResource)
      dietModule <- DietModule.create(transactor)
      trainingModule <- TrainingModule.create(transactor)
      scheduleModule <- ScheduleModule.create(transactor, dietModule.dietRepository, trainingModule.trainingRepository)
      api = new HttpApi(dietModule.dietController, trainingModule.trainingController, scheduleModule.scheduleController)
      _ <- BlazeServerBuilder[IO].bindHttp(config.http.port, config.http.host).withHttpApp(api.httpApp).serve
    } yield ()
  }
}
