package cluster

import akka.actor.{ Actor, ActorLogging, Props }
import akka.cluster.Cluster
import akka.cluster.ClusterEvent._

class ClusterInformer extends Actor with ActorLogging {
  val cluster = Cluster(context.system)

  override def preStart(): Unit = {
    cluster.subscribe(self, initialStateMode = InitialStateAsSnapshot, classOf[ReachabilityEvent], classOf[MemberEvent])
  }

  override def postStop(): Unit = cluster.unsubscribe(self)

  override def receive: Receive = {
    case MemberJoined(member) => log.info(s"Member ${member.address} joined the cluster")
    case MemberUp(member) => log.info(s"Member ${member.address} is UP")
    case MemberRemoved(member, previousStatus) => log.info(s"Member ${member.address} is removed. Previous status was: $previousStatus")
    case memberEvent => log.info(s"Unhandled event received: $memberEvent")
  }
}

object ClusterInformer {
  def props: Props = Props(new ClusterInformer)
  val ClusterInformerName = "cluster-informer"
}
