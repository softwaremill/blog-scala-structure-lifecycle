package com.softwaremill.cats

import cats.effect._
import cats.syntax.all._
import com.softwaremill.cats.config.Config
import com.softwaremill.cats.config.Config.AppConfig
import com.softwaremill.cats.database.DatabaseModule
import com.softwaremill.cats.diet.DietModule
import com.softwaremill.cats.http.HttpApi
import com.softwaremill.cats.schedule.ScheduleModule
import com.softwaremill.cats.training.TrainingModule
import doobie.util.transactor.Transactor
import org.http4s.server.blaze.BlazeServerBuilder

class MainModule {

  def start()(implicit concurrentEffect: ConcurrentEffect[IO], timer: Timer[IO]): IO[Unit] = {
    for {
      config <- Config.loadSync
      transactorResource = DatabaseModule.createTransactor(config.database)
      _ <- transactorResource.use(transactor => program(config, transactor))
    } yield ()
  }

  def program(appConfig: AppConfig, transactor: Transactor[IO])(implicit ce: ConcurrentEffect[IO], timer: Timer[IO]): IO[Unit] = {
    for {
      dietModule <- DietModule.createSync(transactor)
      trainingModule <- TrainingModule.createSync(transactor)
      scheduleModule <- ScheduleModule.createSync(transactor, dietModule.dietRepository, trainingModule.trainingRepository)
      api = new HttpApi(dietModule.dietController, trainingModule.trainingController, scheduleModule.scheduleController)
      _ <- BlazeServerBuilder[IO].bindHttp(appConfig.http.port, appConfig.http.host).withHttpApp(api.httpApp).serve.compile.drain
    } yield ()
  }
}
