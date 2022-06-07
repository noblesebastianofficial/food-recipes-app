FROM adoptopenjdk:11-jre-openj9

LABEL maintainer="noblesebastiank@gmail.com"


WORKDIR /usr/local/bin/

COPY target/food-recipes-service-1.0.1.0-SNAPSHOT.jar webapp.jar

CMD ["java","-jar","webapp.jar"]