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

  def start[F[_]: ContextShift: ConcurrentEffect: Timer](): F[Unit] = {
    for {
      config <- Config.loadSync[F]
      transactorResource = DatabaseModule.createTransactor(config.database)
      _ <- transactorResource.use(transactor => program(config, transactor))
    } yield ()
  }

  def program[F[_]: ConcurrentEffect: Timer](appConfig: AppConfig, transactor: Transactor[F]): F[Unit] = {
    for {
      dietModule <- DietModule.createSync[F](transactor)
      trainingModule <- TrainingModule.createSync[F](transactor)
      scheduleModule <- ScheduleModule.createSync[F](transactor, dietModule.dietRepository, trainingModule.trainingRepository)
      api = new HttpApi[F](dietModule.dietController, trainingModule.trainingController, scheduleModule.scheduleController)
      _ <- BlazeServerBuilder[F].bindHttp(appConfig.http.port, appConfig.http.host).withHttpApp(api.httpApp).serve.compile.drain
    } yield ()
  }
}
