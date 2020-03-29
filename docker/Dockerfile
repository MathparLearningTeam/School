FROM tomcat:9-jdk11-openjdk-slim

RUN rm -r webapps/* | true
ADD target/SchoolModule.war /usr/local/tomcat/webapps/ROOT.war