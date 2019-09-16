package com.softwaremill.fs2.diet

import cats.effect.Sync
import com.softwaremill.fs2.diet.application.{DietRepository, DietService}
import com.softwaremill.fs2.diet.rest.DietController
import doobie.util.transactor.Transactor
import fs2.Stream

class DietModule[F[_]: Sync](transactor: Transactor[F]) {

  lazy val dietRepository = new DietRepository[F](transactor)
  lazy val dietService = new DietService[F](dietRepository)
  lazy val dietController = new DietController(dietService)
}
object DietModule {

  def createSync[F[_]: Sync](transactor: Transactor[F]): F[DietModule[F]] = Sync[F].delay(new DietModule[F](transactor))

  def create[F[_]: Sync](transactor: Transactor[F]): Stream[F, DietModule[F]] = Stream.eval(createSync(transactor))

}
