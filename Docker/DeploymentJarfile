FROM ubuntu:18.04
RUN apt-get update
RUN apt-get install openjdk-8-jdk -y
MAINTAINER BRAJESH KUMAR(bk04031997@gmail.com)
COPY /target/feed-0.0.1-SNAPSHOT.jar /FeedGenerator/feed-0.0.1-SNAPSHOT.jar
WORKDIR /FeedGenerator/
CMD ["java","-jar","/feed-0.0.1-SNAPSHOT.jar"]

