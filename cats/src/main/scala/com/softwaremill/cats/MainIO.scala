package com.softwaremill.cats

import cats.effect._
import cats.syntax.all._

object MainIO extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = (new MainModule).start[IO]().as(ExitCode.Success)
}
