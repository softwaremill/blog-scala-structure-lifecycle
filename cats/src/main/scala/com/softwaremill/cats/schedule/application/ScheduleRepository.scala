package com.softwaremill.cats.schedule.application

import com.softwaremill.cats.schedule.domain.Schedule
import doobie.util.transactor.Transactor

class ScheduleRepository[F[_]](transactor: Transactor[F]) {
  def getSchedules(): F[List[Schedule]] = ??? // use transactor
}
