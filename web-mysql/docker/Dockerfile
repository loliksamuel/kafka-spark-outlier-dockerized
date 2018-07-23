FROM java:8

#WORKDIR .

COPY web-mysql/target/web-mysql-0.0.1-SNAPSHOT.jar  /outlier.detection/web-mysql.jar

RUN bash -c 'touch /outlier.detection/web-mysql.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/outlier.detection/web-mysql.jar"]
