FROM ubuntu:18.04
RUN apt-get update
RUN apt-get install openjdk-8-jdk -y
MAINTAINER BRAJESH KUMAR(bk04031997@gmail.com)
RUN mkdir /opt/tomcat/
RUN curl -O https://downloads.apache.org/tomcat/tomcat-9/v9.0.35/bin/embed/apache-tomcat-9.0.35-embed.tar.gz
RUN tar xvfz apache*.tar.gz
RUN mv apache-tomcat-9.0.35/* /opt/tomcat/.
WORKDIR /opt/tomcat/webapps
COPY SportsLottery.war  /opt/tomcat/webapps
EXPOSE 8080
CMD ["/opt/tomcat/bin/catalina.sh", "run"]

