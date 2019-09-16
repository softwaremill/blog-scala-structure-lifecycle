package com.softwaremill.traits.schedule.application

import com.softwaremill.traits.schedule.domain.Schedule
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future

class ScheduleRepository(database: Database) {
  def getSchedules(): Future[List[Schedule]] = ??? // use database
}
