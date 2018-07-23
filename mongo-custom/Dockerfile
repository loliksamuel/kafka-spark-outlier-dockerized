FROM mongo:2.6.11

RUN bash -c 'apt-get update && apt-get --assume-yes install procps supervisor'
RUN mkdir -p /var/log/supervisor

COPY mongo-custom/init_mongo.js  /init_mongo.js
COPY mongo-custom/start_mongod.sh  /start_mongod.sh
COPY mongo-custom/supervisord.conf /etc/supervisor/conf.d/supervisord.conf


CMD ["/usr/bin/supervisord", "-n"]