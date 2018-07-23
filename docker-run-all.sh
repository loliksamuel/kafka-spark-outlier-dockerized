#!/usr/bin/env bash
source dockerUtils.sh
ensure-docker-up && \
delete-container-for-image outlier-detection/kafka-broker && \
delete-container-for-image outlier-detection/mongo-custom && \
delete-container-for-image outlier-detection/web-mongo && \
delete-container-for-image outlier-detection/spark-consumer && \
delete-container-for-image outlier-detection/python-producer && \
#
# create & start  all the services (containers) defined in a docker-compose.yml
docker-compose up --force-recreate