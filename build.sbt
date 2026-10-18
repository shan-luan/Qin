val scala3Version = "3.10.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "QIN",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    scalacOptions += "-deprecation",

    libraryDependencies += "org.scalameta" %% "munit" % "1.3.6" % Test
  )
