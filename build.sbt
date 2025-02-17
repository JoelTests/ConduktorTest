ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.5"

lazy val root = (project in file("."))
  .settings(
    name := "ConduktorTest"
  )

libraryDependencies ++= Seq(
  "org.apache.kafka" % "kafka-clients" % "3.9.0",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.15.1",
  "com.github.pureconfig" %% "pureconfig-core" % "0.17.8",
  "com.google.code.gson" % "gson" % "2.8.2",
  "org.http4s"    %% "http4s-blaze-server" % "1.0.0-M41",
  "org.http4s"    %% "http4s-dsl"          % "1.0.0-M44",
  "org.typelevel" %% "log4cats-slf4j"      % "2.7.0"
)

