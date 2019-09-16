package com.softwaremill.cats.diet

import cats.effect.Sync
import com.softwaremill.cats.diet.application.{DietRepository, DietService}
import com.softwaremill.cats.diet.rest.DietController
import doobie.util.transactor.Transactor

class DietModule[F[_]: Sync](transactor: Transactor[F]) {

  lazy val dietRepository = new DietRepository[F](transactor)
  lazy val dietService = new DietService[F](dietRepository)
  lazy val dietController = new DietController(dietService)
}
object DietModule {

  def createSync[F[_]: Sync](transactor: Transactor[F]): F[DietModule[F]] = Sync[F].delay(new DietModule[F](transactor))
}
