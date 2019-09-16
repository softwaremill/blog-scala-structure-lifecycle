package com.softwaremill.fs2.schedule.application

import com.softwaremill.fs2.schedule.domain.Schedule
import doobie.util.transactor.Transactor

class ScheduleRepository[F[_]](transactor: Transactor[F]) {
  def getSchedules(): F[List[Schedule]] = ??? // use transactor
}
