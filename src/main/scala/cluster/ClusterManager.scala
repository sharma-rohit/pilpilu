package cluster

import akka.actor.{ ActorRef, ActorSystem }

class ClusterManager(implicit actorSystem: ActorSystem) {
  private def startClusterInformer(): ActorRef = actorSystem.actorOf(ClusterInformer.props, ClusterInformer.ClusterInformerName)

  def setupCluster(): Unit = {
    startClusterInformer()
  }
}
