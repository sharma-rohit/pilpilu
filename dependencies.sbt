val Akka = "2.5.11"
val AkkaHttp = "10.1.0"
val AkkaStream = "2.5.11"
val Circe = "0.9.1"
val CodahaleMetrics = "3.0.2"
val AmazonSdk2 = "2.0.0-preview-10"

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

  "software.amazon.awssdk" % "aws-sdk-java" % AmazonSdk2
) ++ circeDependencies





