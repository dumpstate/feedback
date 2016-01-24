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


libraryDependencies ++= {
  val akkaVersion = "2.4.1"
  val sprayVersion = "1.3.3"
  Seq(
    "io.spray" %% "spray-can" % sprayVersion,
    "io.spray" %% "spray-routing" % sprayVersion,
    "com.typesafe.akka" %% "akka-actor" % akkaVersion
  )
}
