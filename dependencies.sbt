val AKKA = "2.5.11"
val AKKA_HTTP = "10.1.0"
val AKKA_STREAMS = "2.5.11"
val CIRCE_VERSION = "0.9.1"

val circeDependencies = Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % CIRCE_VERSION)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % AKKA,
  "com.typesafe.akka" %% "akka-testkit" % AKKA % Test,

  "com.typesafe.akka" %% "akka-http" % AKKA_HTTP,
  "com.typesafe.akka" %% "akka-http-testkit" % AKKA_HTTP % Test,


  "com.typesafe.akka" %% "akka-stream" % AKKA_STREAMS,
  "com.typesafe.akka" %% "akka-stream-testkit" % AKKA_STREAMS % Test,

  "com.typesafe.akka" %% "akka-cluster" % AKKA,
  "com.typesafe.akka" %% "akka-cluster-sharding" % AKKA
) ++ circeDependencies





