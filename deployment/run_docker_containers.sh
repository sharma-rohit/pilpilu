#!/usr/bin/env bash
sbt docker:publishLocal

AKKA_HOSTNAME=`ipconfig getifaddr en0`
AKKA_SEED_NODES=$AKKA_HOSTNAME:2551,$AKKA_HOSTNAME:2552,$AKKA_HOSTNAME:2553

docker network create --subnet=172.18.0.0/16 akka_cluster_network

for port in `seq 2551 2553`;
do
    DOCKER_CONTAINER_IP=172.18.0.$(($port - 2548))
    echo $DOCKER_CONTAINER_IP
    docker run -d \
    --net akka_cluster_network \
    -p $port:$port -p $((6449 + $port)):9000 \
    -e BUILD_ENV=dev \
    -e AKKA_PORT=$port \
    -e AKKA_BIND_PORT=$port \
    -e AKKA_HOSTNAME=$AKKA_HOSTNAME \
    -e AKKA_SEED_NODES=$AKKA_SEED_NODES \
    -e AKKA_BIND_HOSTNAME=$DOCKER_CONTAINER_IP \
    --ip $DOCKER_CONTAINER_IP \
    pilpilu:0.1.0
done