name := "blog-structure-lifecycle"

version := "0.1"

scalaVersion := "2.13.0"

val akkaHttp = Seq("com.typesafe.akka" %% "akka-http"   % "10.1.9" ,
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.9",
  "com.typesafe.akka" %% "akka-stream" % "2.5.25")
val slick = Seq(  "com.typesafe.slick" %% "slick" % "3.3.2",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.2")
val fs2Stream = Seq("co.fs2" %% "fs2-core" % "2.0.0")
val catsEffect = Seq("org.typelevel" %% "cats-effect" % "2.0.0")
val doobie = Seq("org.tpolecat" %% "doobie-core" % "0.8.0-RC1", "org.tpolecat" %% "doobie-hikari" % "0.8.0-RC1")
val http4s = Seq("org.http4s" %% "http4s-blaze-server" % "0.21.0-M4", "org.http4s" %% "http4s-dsl" % "0.21.0-M4")
val pureConfig = Seq("com.github.pureconfig" %% "pureconfig" % "0.12.0")

lazy val traits = (project in file("traits"))
  .settings(scalaVersion := "2.13.0",
    libraryDependencies ++= pureConfig ++ akkaHttp ++ slick)
lazy val classes = (project in file("classes"))
  .settings(scalaVersion := "2.13.0",
    libraryDependencies ++= pureConfig ++ akkaHttp ++ slick)
lazy val cats = (project in file("cats"))
  .settings(
    scalaVersion := "2.13.0",
    libraryDependencies ++= catsEffect ++ http4s ++ doobie ++ pureConfig
  )
lazy val fs2 = (project in file("fs2"))
  .settings(
    scalaVersion := "2.13.0",
    libraryDependencies ++= catsEffect ++ http4s ++ fs2Stream ++ doobie ++ pureConfig
  )
