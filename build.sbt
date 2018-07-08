import com.typesafe.sbt.packager.docker.{Cmd, ExecCmd}

name := "pilpilu"

version := "0.1.1"

scalaVersion := "2.12.5"

lazy val root = (project in file(".")).enablePlugins(JavaAppPackaging, DockerPlugin)

maintainer in Docker := "rohitsharma9204@gmail.com"
dockerBaseImage := "openjdk:10"
dockerExposedPorts := Seq(9000)
dockerRepository := Some("localhost:5000")
daemonUser in Docker := "root"