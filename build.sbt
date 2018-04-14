name := "pilpilu"

version := "0.1.0"

scalaVersion := "2.12.5"

lazy val root = (project in file(".")).enablePlugins(JavaAppPackaging, DockerPlugin)

maintainer in Docker := "rohitsharma9204@gmail.com"
dockerBaseImage := "openjdk:10"
dockerExposedPorts := Seq(9000)