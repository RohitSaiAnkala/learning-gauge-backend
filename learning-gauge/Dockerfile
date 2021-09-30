FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.5_10

ARG tier=dms-api
ARG serviceName
ENV APPDYNAMICS_AGENT_APPLICATION_NAME "dms_api"
ENV APPDYNAMICS_AGENT_TIER_NAME $tier

ADD app.jar app.jar

EXPOSE 8080

CMD ["java","-XX:MinRAMPercentage=25.0","-XX:MaxRAMPercentage=80.0","-XshowSettings:vm","-Djava.security.egd=file:/dev/./urandom","-Dfile.encoding=UTF-8","-jar","app.jar"]
