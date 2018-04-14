import com.typesafe.config.{ Config, ConfigFactory, ConfigValueFactory }
import common.Constant.ACTOR_SYSTEM_NAME
import utility.CustomException.ConfigMissingException
import utility.EnvironmentVariableReader

object SystemConfigService {
  private val seedNodesOpt: Option[Array[String]] = EnvironmentVariableReader.read("AKKA_SEED_NODES")(_.split(","))
  private val hostNameOpt = EnvironmentVariableReader.read("AKKA_HOSTNAME")(identity)
  private val portOpt = EnvironmentVariableReader.read("AKKA_PORT")(_.toInt)

  private def resolveConfig(seedNodes: Iterable[String], hostName: String, port: Int): Config = {
    import scala.collection.JavaConverters._
    ConfigFactory.empty()
      .withValue("akka.cluster.seed-nodes", ConfigValueFactory.fromIterable(seedNodes.map(seedNode => s"akka.tcp://$ACTOR_SYSTEM_NAME@$seedNode").asJava))
      .withValue("akka.remote.netty.tcp.hostname", ConfigValueFactory.fromAnyRef(hostName))
      .withValue("akka.remote.netty.tcp.port", ConfigValueFactory.fromAnyRef(port))
      .withValue("akka.remote.netty.tcp.bind-hostname", ConfigValueFactory.fromAnyRef(EnvironmentVariableReader.read("AKKA_BIND_HOSTNAME")(identity).get))
      .withValue("akka.remote.netty.tcp.bind-port", ConfigValueFactory.fromAnyRef(EnvironmentVariableReader.read("AKKA_BIND_PORT")(_.toInt).get))
      .withFallback(ConfigFactory.load())
      .resolve()
  }

  val config: Config = (for {
    seedNodes <- seedNodesOpt
    hostName <- hostNameOpt
    port <- portOpt
  } yield {
    resolveConfig(seedNodes, hostName, port)
  }).getOrElse(throw new ConfigMissingException("Some of the configs are missing"))

}
