FROM openjdk:11-jdk

LABEL maintainer="noblesebastiank@gmail.com"


WORKDIR /usr/local/bin/

COPY target/user-authentication-1.0.1.0-SNAPSHOT.jar webapp.jar

CMD ["java","-jar","webapp.jar"]