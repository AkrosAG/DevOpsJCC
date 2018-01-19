FROM openjdk:8

COPY  target/DevOpsJCC*.jar  /opt/jcc/DevOpsJCC.jar

EXPOSE 666

ENTRYPOINT java -jar /opt/jcc/DevOpsJCC.jar