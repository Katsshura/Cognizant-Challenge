FROM maven:3.6.3 as DEPS

WORKDIR /opt/app
COPY api/pom.xml api/pom.xml
COPY domain/pom.xml domain/pom.xml
COPY infrastructure/pom.xml infrastructure/pom.xml

COPY pom.xml .
RUN mvn -B -e -C org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline

FROM maven:3.6.3 as BUILDER
WORKDIR /opt/app
COPY --from=deps /root/.m2 /root/.m2
COPY --from=deps /opt/app/ /opt/app
COPY api/src /opt/app/api/src
COPY domain/src /opt/app/domain/src
COPY infrastructure/src /opt/app/infrastructure/src

RUN mvn -B -e -o clean install -DskipTests=true

FROM openjdk:15
WORKDIR /opt/app
COPY --from=builder /opt/app/api/target/api-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD [ "java", "-jar", "/opt/app/api-0.0.1-SNAPSHOT.jar" ]