package http.route

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{ complete, get, path }
import akka.http.scaladsl.server.Route

object PingRoute extends BaseRoute {
  override val route: Route = get {
    path("ping") {
      complete(StatusCodes.OK)
    }
  }
}
