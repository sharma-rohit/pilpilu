package http.route

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives._
import cluster.http.route.CacheRoute

class CombinedRoute(implicit actorSystem: ActorSystem) extends BaseRoute {
  override val route = PingRoute.route ~ new CacheRoute().route
}
