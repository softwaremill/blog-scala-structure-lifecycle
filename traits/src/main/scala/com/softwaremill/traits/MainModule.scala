package com.softwaremill.traits

import com.softwaremill.traits.database.DatabaseModule
import com.softwaremill.traits.diet.DietModule
import com.softwaremill.traits.http.HttpApi
import com.softwaremill.traits.schedule.ScheduleModule
import com.softwaremill.traits.training.TrainingModule
import akka.http.scaladsl.server.Directives._
import com.softwaremill.traits.config.ConfigModule

import scala.concurrent.duration._
import scala.concurrent.Await
import scala.util.Try

trait MainModule extends ConfigModule with DatabaseModule with DietModule with TrainingModule with ScheduleModule with HttpApi {

  override def routes = dietController.routes ~ trainingController.routes ~ scheduleController.routes

  def start(): Unit = {
    startHttpApi()
  }

  def stop(): Unit = {
    Try(closeSql())
    Try(Await.ready(stopHttpApi(), 15.seconds))
  }
}
