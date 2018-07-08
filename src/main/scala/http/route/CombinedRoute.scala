package http.route

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives._

class CombinedRoute(implicit actorSystem: ActorSystem) extends BaseRoute {
  override val route = PingRoute.route ~ new CacheRoute().route
}
