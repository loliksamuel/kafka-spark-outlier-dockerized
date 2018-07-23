FROM spotify/kafka 

#RUN bash -c 'apt-get update && apt-get --assume-yes install supervisor'
#RUN mkdir -p /var/log/supervisor

COPY kafka-broker/initialize.sh  /initialize.sh

#COPY kafka-broker/supervisord.conf /etc/supervisor/conf.d/supervisord.conf

ENTRYPOINT ["/initialize.sh"]