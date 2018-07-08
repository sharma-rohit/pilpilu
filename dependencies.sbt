val Akka = "2.5.11"
val AkkaHttp = "10.1.0"
val AkkaStream = "2.5.11"
val Circe = "0.9.1"
val CodahaleMetrics = "3.0.2"
val AkkaManagement = "0.15.0"
val AkkaHttpCirceVersion = "1.19.0"

val circeDependencies = Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % Circe)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % Akka,
  "com.typesafe.akka" %% "akka-testkit" % Akka % Test,

  "com.typesafe.akka" %% "akka-http" % AkkaHttp,
  "com.typesafe.akka" %% "akka-http-testkit" % AkkaHttp % Test,


  "com.typesafe.akka" %% "akka-stream" % AkkaStream,
  "com.typesafe.akka" %% "akka-stream-testkit" % AkkaStream % Test,

  "com.typesafe.akka" %% "akka-cluster" % Akka,
  "com.typesafe.akka" %% "akka-cluster-sharding" % Akka,

  "com.codahale.metrics" % "metrics-core" % CodahaleMetrics,

  "com.lightbend.akka.management" %% "akka-management-cluster-bootstrap" % AkkaManagement,
  "com.lightbend.akka.discovery" %% "akka-discovery-dns" % AkkaManagement,

  "de.heikoseeberger" %% "akka-http-circe" % AkkaHttpCirceVersion

) ++ circeDependencies





