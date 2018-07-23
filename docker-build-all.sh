#!/usr/bin/env bash

function mvn-there() {
  DIR="$1"
  shift
  (cd $DIR; mvn "$@")     
} 

source dockerUtils.sh

ensure-docker-up && \
delete-container-and-image outlier-detection/kafka-broker && \
delete-container-and-image outlier-detection/spark-consumer     && \
delete-container-and-image outlier-detection/web-mongo         && \
delete-container-and-image outlier-detection/mongo-custom      && \
delete-container-and-image outlier-detection/python-producer   && \
mvn-there ./spark-consumer clean compile package && \
mvn-there ./web-mongo  clean compile package    && \
#
# builds a new image  with -t ( tag name ) and -f ( Dockerfile )
docker build -t outlier-detection/kafka-broker -f ./kafka-broker/Dockerfile .    && \
docker build -t outlier-detection/spark-consumer     -f ./spark-consumer/docker/Dockerfile . && \
docker build -t outlier-detection/web-mongo         -f ./web-mongo/docker/Dockerfile .     && \
docker build -t outlier-detection/mongo-custom      -f ./mongo-custom/Dockerfile .         && \
docker build -t outlier-detection/python-producer   -f ./python-producer/Dockerfile .




# docker build -t outlier-detection/mysql             -f                ./mysql/Dockerfile . && \
# docker build -t outlier-detection/web-mysql         -f     ./web-mysql/docker/Dockerfile . && \