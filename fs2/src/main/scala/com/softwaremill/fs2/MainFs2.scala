package com.softwaremill.fs2

import cats.effect._
import cats.syntax.all._

object MainFs2 extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = (new MainModule).start().compile.drain.as(ExitCode.Success)
}
