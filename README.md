# F5 OUTLIER DETECTION USING SPARK KAFKA MONGO 

1. Run ./docker-build-all.sh  
2. Run ./docker-start-all.sh  
3. u may have to add to etc/host 127.0.0.1 kafka_host

# known issue
SparkException: Job aborted due to stage failure: Task 0 in stage 110.0 failed 1 times, most recent failure: Lost task 0.0 in stage 110.0 (TID 110, localhost, executor driver): java.lang.AbstractMethodError

