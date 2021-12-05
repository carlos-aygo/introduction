FROM openjdk:11
COPY build/libs/introduction-*-all.jar introduction.jar
EXPOSE 8080
CMD java -Dcom.sun.management.jmxremote -noverify ${JAVA_OPTS} -jar introduction.jar