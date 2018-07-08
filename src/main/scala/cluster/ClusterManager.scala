package cluster

import akka.actor.{ActorRef, ActorSystem}
import akka.management.AkkaManagement
import akka.management.cluster.bootstrap.ClusterBootstrap

class ClusterManager(implicit actorSystem: ActorSystem) {
  private def startClusterInformer(): ActorRef = actorSystem.actorOf(ClusterInformer.props, ClusterInformer.ClusterInformerName)

  def setupCluster(): Unit = {
    AkkaManagement(actorSystem).start()
    ClusterBootstrap(actorSystem).start()
    startClusterInformer()
  }
}
