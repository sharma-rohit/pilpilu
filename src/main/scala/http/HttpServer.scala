package http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import http.route.CombinedRoute

import scala.concurrent.Future

class HttpServer(implicit val actorSystem: ActorSystem, materializer: ActorMaterializer) {
  val combinedRoute = new CombinedRoute()
  def start: Future[Http.ServerBinding] = Http().bindAndHandle(combinedRoute.route, "0.0.0.0", 9000)
}
