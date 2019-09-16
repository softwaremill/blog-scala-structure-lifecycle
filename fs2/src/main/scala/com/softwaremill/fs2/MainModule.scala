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

  def start[F[_]: ContextShift: ConcurrentEffect: Timer](): Stream[F, Unit] = {
    for {
      config <- Config.load[F]
      transactorResource = DatabaseModule.createTransactor(config.database)
      transactor <- Stream.resource(transactorResource)
      dietModule <- DietModule.create[F](transactor)
      trainingModule <- TrainingModule.create[F](transactor)
      scheduleModule <- ScheduleModule.create[F](transactor, dietModule.dietRepository, trainingModule.trainingRepository)
      api = new HttpApi[F](dietModule.dietController, trainingModule.trainingController, scheduleModule.scheduleController)
      _ <- BlazeServerBuilder[F].bindHttp(config.http.port, config.http.host).withHttpApp(api.httpApp).serve
    } yield ()
  }
}
