organization := "com.dumpstate"

name := "feedback"

scalaVersion := "2.11.7"

scalacOptions ++= Seq(
  "-target:jvm-1.7",
  "-encoding", "UTF-8",
  "-deprecation",
  "-feature",
  "-unchecked",
  "-Xlint",
  "-Ywarn-adapted-args",
  "-Ywarn-value-discard",
  "-Ywarn-inaccessible",
  "-Ywarn-dead-code"
)

licenses +=("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0.html"))

enablePlugins(JavaAppPackaging)

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  "com.typesafe.akka" %% "akka-actor" % "2.4.1",
  "com.typesafe.akka" %% "akka-http-experimental" % "2.0.2",
  "com.typesafe.akka" %% "akka-http-spray-json-experimental" % "2.0.2",
  "org.scalaz" %% "scalaz-core" % "7.2.0"
)
