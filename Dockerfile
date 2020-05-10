# For Java 8
FROM maven:openjdk AS build

WORKDIR /tar/

COPY . /tar/
# Maven build
RUN ["mvn","package","-DskipTests"]

#ARG WAR_FILE=/target/InventoryManagementSystem.war

# cd /opt/app
# WORKDIR /target/

#COPY ${WAR_FILE} inventory.war

FROM tomcat:8.0.20-jre8
COPY --from=build /tar/target/*.war /usr/local/tomcat/webapps/InventoryManagementSystem.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
