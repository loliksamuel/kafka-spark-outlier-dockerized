#base image
#FROM java:8
FROM isuper/java-oracle:jdk_8
MAINTAINER Sam Cardonis




RUN curl -O http://www.eu.apache.org/dist/spark/spark-2.3.1/spark-2.3.1-bin-hadoop2.6.tgz    && \
    tar xvf spark-2.3.1-bin-hadoop2.6.tgz   && \
 	rm spark-2.3.1-bin-hadoop2.6.tgz

RUN mkdir -p /var/log/spark

COPY spark-consumer/target/spark-consumer-0.0.1-SNAPSHOT-allinone.jar  /spark/spark-consumer.jar
COPY spark-consumer/docker/log4j.properties /spark-2.3.1-bin-hadoop2.6/conf/log4j.properties

RUN bash -c 'touch /spark/spark-consumer.jar'
#RUN zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties
# launch
ENTRYPOINT ["spark-2.3.1-bin-hadoop2.6/bin/spark-submit","--class","outlier.detection.spark._MainApp", \
"--master", "local[*]", "/spark/spark-consumer.jar", "kafka_host:2181", "group1", "test", "3"]
