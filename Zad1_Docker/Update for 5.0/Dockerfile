# Base image 
FROM ubuntu:22.04

RUN apt-get update && apt-get install -y software-properties-common apt-utils wget unzip openjdk-8-jdk

RUN wget -q https://github.com/JetBrains/kotlin/releases/download/v1.9.10/kotlin-compiler-1.9.10.zip \
  && unzip kotlin-compiler-1.9.10.zip \
  && mv kotlinc /usr/local/bin \
  && rm kotlin-compiler-1.9.10.zip

ENV PATH=$PATH:/usr/local/bin/kotlinc/bin

RUN kotlinc -version

RUN java -version


RUN wget -q https://services.gradle.org/distributions/gradle-8.4-bin.zip -P /tmp \
  && unzip -d /opt/gradle /tmp/gradle-*.zip \
  && rm /tmp/gradle-*.zip

ENV PATH=$PATH:/opt/gradle/gradle-8.4/bin

RUN gradle -v

WORKDIR /app

COPY ./src /app/src
COPY build.gradle /app

RUN gradle build

CMD ["gradle", "run"]