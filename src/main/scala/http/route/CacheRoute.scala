package http.route

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.pattern.ask
import akka.util.Timeout
import cluster.EntityActor.{CacheData, GetCachedData}
import cluster.RegionActor
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import scala.concurrent.duration._
import scala.util.{Failure, Success}

class CacheRoute(implicit actorSystem: ActorSystem) extends BaseRoute {
  private val region = new RegionActor().region
  implicit val timeout: Timeout=  3.seconds
  override val route: Route = post {
    path("cache") {
      entity(as[CacheData]) { cacheData =>
        onComplete((region ? cacheData).mapTo[String]) {
          case Success(s) => complete(s)
          case Failure(f) => complete(f)
        }
      }
    }
  } ~ get {
    path("cache" / Segment) { id =>
      onComplete((region ? GetCachedData(id)).mapTo[Option[String]]) {
        case Success(s) => complete(s)
        case Failure(f) => complete(f)
      }
    }
  }
}
