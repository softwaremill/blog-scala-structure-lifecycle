package com.softwaremill.classes

import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, Materializer}
import com.softwaremill.classes.config.Config
import com.softwaremill.classes.database.DatabaseModule
import com.softwaremill.classes.diet.DietModule
import com.softwaremill.classes.http.HttpApi
import com.softwaremill.classes.schedule.ScheduleModule
import com.softwaremill.classes.training.TrainingModule

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.Try

class MainModule {

  private[this] implicit lazy val system: ActorSystem = ActorSystem("classes")
  import system.dispatcher

  private[this] implicit lazy val materializer: ActorMaterializer = ActorMaterializer()(system)

  private[this] lazy val appConfig = Config.load()

  private[this] lazy val databaseModule = new DatabaseModule(appConfig.database)

  private[this] lazy val dietModule = new DietModule(databaseModule.database)
  private[this] lazy val trainingModule = new TrainingModule(databaseModule.database)
  private[this] lazy val scheduleModule =
    new ScheduleModule(databaseModule.database, dietModule.dietRepository, trainingModule.trainingRepository)

  private[this] lazy val httpApi =
    new HttpApi(appConfig.http, dietModule.dietController, trainingModule.trainingController, scheduleModule.scheduleController)

  def start(): Unit = {
    httpApi.startHttpApi()
  }

  def stop(): Unit = {
    Try(databaseModule.closeSql())
    Try(Await.ready(httpApi.stopHttpApi(), 15.seconds))
    Try(Await.ready(system.terminate(), 15.seconds))
  }
}
