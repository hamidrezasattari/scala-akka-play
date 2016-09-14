name := """reactivePrg"""

version := "1.0"

scalaVersion := "2.11.7"

name := "Effective Akka"
val playVersion = "2.5.4"
val akkaVersion= "2.3.12"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "com.typesafe.play" %% "play-ws" % playVersion

)

