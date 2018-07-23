FROM java:8
MAINTAINER Sam Cardonis
#WORKDIR .

COPY web-mongo/target/web-mongo-0.0.1-SNAPSHOT.jar  /outlier.detection/web-mongo.jar

RUN bash -c 'touch /outlier.detection/web-mongo.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/outlier.detection/web-mongo.jar"]
