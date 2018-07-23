#!/usr/bin/env bash

ensure_topic_exists() {
	local existing_topic_descr="$("$KAFKA_BIN"/kafka-topics.sh --describe --topic "$TOPIC_NAME" --zookeeper "$ZK")"
	
	echo "existing_topic_descr:$existing_topic_descr"
	
	if [[ "$existing_topic_descr" == *"PartitionCount:"* ]] ; then
		echo "topic already exists"
	else
		echo "about to create topic: $TOPIC_NAME"
		"$KAFKA_BIN"/kafka-topics.sh --create --topic "$TOPIC_NAME" --partitions 1 --replication-factor=1 --zookeeper "$ZK"				
	fi
}

declare -r TOPIC_NAME="test"
declare -r KAFKA_BIN="$KAFKA_HOME/bin"
declare -r ZK="kafka_host:2181"

ensure_topic_exists