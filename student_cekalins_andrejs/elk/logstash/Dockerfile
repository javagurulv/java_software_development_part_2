FROM docker.elastic.co/logstash/logstash:7.2.0
COPY logstash.conf /usr/share/logstash/logstash.conf
USER root
RUN chown root /usr/share/logstash/logstash.conf

