#!/usr/bin/env python
import threading, logging, time

import sys

from kafka.client import KafkaClient
from kafka.consumer import SimpleConsumer
from kafka.producer import SimpleProducer

from kafka.common import LeaderNotAvailableError, KafkaUnavailableError, KafkaError, ConnectionError

from random import randint, Random
from datetime import datetime, timedelta, tzinfo
import json

class Producer:
    #daemon = True
    #daemon = False

    def run(self):
        #client = KafkaClient("localhost:9092")
        client = KafkaClient("kafka_host:9092")
        producer = SimpleProducer(client)

        while True:
            try:
              messages = []
              for i in xrange(1, 10):
                messageStr = SelfGeneratedMessage().asJson()
                logger.debug('Generated message: %s', messageStr)
                messages.append(messageStr)
            
              producer.send_messages('test', *messages)    
#            producer.send_messages('test', '{"publisher": "publisher-id", "time": "2015-11-03 15:03:30.352", "readings": [ 1, 1,1,1,1,1,1,1,1,1,1,1,4,3,3,3,32,2,1,1,1,1]}')

              time.sleep(1)
            except LeaderNotAvailableError as e:
              logging.exception('LeaderNotAvailableError')
              time.sleep(10)
            except KafkaUnavailableError as e:
              logging.exception('KafkaUnavailableError')
              time.sleep(30)
            except ConnectionError as e:
              logging.exception('ConnectionError')
              time.sleep(60)    
            except KafkaError as e:
              logging.exception('KafkaError')
              time.sleep(60) 
            except Exception as e:
              logging.exception('Exception')
              time.sleep(60) 

class Consumer(threading.Thread):
    daemon = True

    def run(self):
        #client = KafkaClient("localhost:9092")
        client = KafkaClient("kafka_host:9092")
#        consumer = SimpleConsumer(client, "test-group", "my-topic")
        consumer = SimpleConsumer(client, "python-group", "test")


        for message in consumer:
            print(message)


class SelfGeneratedMessage:
    """SelfGeneratedMessage"""
   
    def asJson(self):
        #nowStr=self.now.strftime("%Y-%m-%d %H:%M:%S.%f")
        nowStr=self.formatTime(self.now)
        publisherIdStr = "publisher-{0}".format(self.publisherId)
        
        info = dict()
        info["publisher"]=publisherIdStr
        info["time"]=nowStr
        info["readings"]=self.readings
        
        res = json.dumps(info)
        return res
    
    def formatTime(self, t):        
        s = t.strftime('%Y-%m-%d %H:%M:%S.%f')
        tail = s[-7:]
        f = round(float(tail), 3)
        temp = "%.3f" % f
        return "%s%s" % (s[:-7], temp[1:])
        
    def __init__(self):
        self.now = datetime.utcnow()
        self.publisherId=randint(0, 5)
        self.r1 = Random()
        self.r2 = Random()
        self.r2.setstate(self.r1.getstate())
        self.r2.jumpahead(1024)
        numReadingsAdditional=randint(0, 5)
        totalReadings = 10+numReadingsAdditional
        self.readings=[]
        for x in xrange(1, totalReadings):
            mean = 2
            reading = mean + randint(0, 5) + self.r1.randint(0,1)*self.r2.randint(0,1)*100  
            self.readings.append(reading)



def main():
   time.sleep(30)    
   while True:
      try:
       Producer().run()
      except:
       logging.exception('Exception in main')
       time.sleep(10)

    

if __name__ == "__main__":
    logging.basicConfig(
        format='%(asctime)s.%(msecs)s:%(name)s:%(thread)d:%(levelname)s:%(process)d:%(message)s',
        level=logging.DEBUG
        )
    logger = logging.getLogger(__name__)   
    main()
