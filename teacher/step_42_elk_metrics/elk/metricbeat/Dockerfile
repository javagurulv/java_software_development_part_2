FROM docker.elastic.co/beats/metricbeat:7.2.0

COPY metricbeat.yml /usr/share/metricbeat/metricbeat.yml

COPY system.yml /usr/share/metricbeat/modules.d/system.yml
COPY http.yml /usr/share/metricbeat/modules.d/http.yml

USER root

RUN chown root /usr/share/metricbeat/metricbeat.yml
