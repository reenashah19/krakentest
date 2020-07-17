FROM openjdk:8-jdk-alpine
FROM maven:3.5-jdk-8-alpine

RUN mkdir -p /usr/test_app
WORKDIR /usr/test_app
COPY . /usr/test_app
CMD ["mvn", "test"]

