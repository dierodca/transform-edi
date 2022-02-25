# Ebusiness for transform-edi-1.0-local-SNAPSHOT
# ./gradlew clean build
# Build image with:  docker build -t transform-edi .

### Cambiar la imagen linux para despligue en produccion
FROM amazoncorretto:11
LABEL key="Carvajal"

ADD build/libs /opt/spring-boot
ADD src/main/resources/config  /opt/config

### Set Environment
ENV SERVER_HOME /opt/spring-boot

WORKDIR /opt/spring-boot

### Open Ports
EXPOSE 9000

### Start instance
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "-Dfile.encoding=UTF-8", "/opt/spring-boot/transform-edi-1.0-local-SNAPSHOT.jar"]