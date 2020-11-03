FROM ubuntu:18.04
RUN apt-get update
RUN apt-get install openjdk-8-jdk -y
MAINTAINER BRAJESH KUMAR(bk04031997@gmail.com)
COPY /target/docker-jar-1.0.jar /Appjar/docker-jar-1.0.jar
WORKDIR /Appjar/
CMD ["java","-jar","docker-jar-1.0.jar"]

