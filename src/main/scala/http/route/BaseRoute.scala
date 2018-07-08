package http.route

import akka.event.Logging
import akka.http.scaladsl.server.Directives.logRequestResult
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.unmarshalling.{ FromEntityUnmarshaller, Unmarshaller }
import io.circe.Decoder
import io.circe.Json
import io.circe.parser.parse

trait BaseRoute {
  def route: Route
  def routeWithLoggingInfo: Route = logRequestResult("Request-Response", Logging.InfoLevel)(route)
}
