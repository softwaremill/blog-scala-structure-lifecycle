package com.softwaremill.fs2.schedule.application

import cats.effect.IO
import com.softwaremill.fs2.schedule.domain.Schedule
import doobie.util.transactor.Transactor

class ScheduleRepository(transactor: Transactor[IO]) {
  def getSchedules(): IO[List[Schedule]] = ??? // use transactor
}
