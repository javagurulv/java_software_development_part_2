FROM docker.elastic.co/beats/metricbeat:7.2.0

COPY metricbeat.yml /usr/share/metricbeat/metricbeat.yml

COPY modules/system.yml /usr/share/metricbeat/modules/system.yml
COPY modules/http.yml /usr/share/metricbeat/modules/http.yml

USER root

RUN chown root /usr/share/metricbeat/metricbeat.yml

