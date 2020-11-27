FROM ubuntu:18.04
RUN apt-get update
RUN apt-get install openjdk-8-jdk -y
MAINTAINER BRAJESH KUMAR(bk04031997@gmail.com)
RUN cd  /opt/
RUN curl -L -O https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-6.7.1.deb
RUN dpkg -i elasticsearch-6.7.1.deb
RUN curl -L -O https://artifacts.elastic.co/downloads/kibana/kibana-6.7.1-amd64.deb
RUN dpkg -i kibana-6.7.1-amd64.deb
RUN curl -L -O https://artifacts.elastic.co/downloads/logstash/logstash-6.7.1.deb
RUN dpkg -i logstash-6.7.1.deb
RUN curl -L -O https://artifacts.elastic.co/downloads/beats/filebeat/filebeat-6.7.1-amd64.deb
RUN dpkg -i filebeat-6.7.1-amd64.deb
WORKDIR /opt/
