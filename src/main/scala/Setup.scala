import akka.actor.ActorSystem
import akka.event.{ Logging, LoggingAdapter }
import akka.stream.ActorMaterializer
import cluster.ClusterManager
import common.Constant.ACTOR_SYSTEM_NAME
import http.HttpServer

import scala.concurrent.ExecutionContext
import scala.util.{ Failure, Success }

trait Setup {
  implicit val actorSystem: ActorSystem = ActorSystem(ACTOR_SYSTEM_NAME, SystemConfigService.config)
  implicit val actorMaterializer: ActorMaterializer = ActorMaterializer()
  val loggingAdapter: LoggingAdapter = Logging(actorSystem, "Setup")
}

object Setup extends Setup {
  def startHttpServer(): Unit = {
    implicit val ec: ExecutionContext = actorSystem.dispatcher
    val httpServer = new HttpServer()
    httpServer.start.onComplete {
      case Success(s) => loggingAdapter.info(s"Http server started at: [${s.localAddress}]")
      case Failure(f) => loggingAdapter.error(f, "Http server can't be started]")
    }
  }

  def startClusterManager(): Unit = new ClusterManager().setupCluster()
}