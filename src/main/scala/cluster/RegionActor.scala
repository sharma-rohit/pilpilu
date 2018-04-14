package cluster

import akka.actor.{ ActorRef, ActorSystem }
import akka.cluster.sharding.{ ClusterSharding, ClusterShardingSettings }

class RegionActor(implicit actorSystem: ActorSystem) {
  val region: ActorRef = ClusterSharding(actorSystem).start(
    typeName = "cache-region",
    entityProps = EntityActor.props,
    settings = ClusterShardingSettings(actorSystem),
    extractEntityId = EntityActor.extractEntityId,
    extractShardId = EntityActor.extractShardId
  )
}
