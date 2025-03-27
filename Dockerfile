FROM openjdk:17-jdk
MAINTAINER apis-it.hr

# Kopiranje JAR datoteke u kontejner
COPY target/parcijalni_ispit-0.0.1-SNAPSHOT.jar parcijalni_ispit.jar

# Otvaranje porta
EXPOSE 8082

# Pokretanje aplikacije
ENTRYPOINT ["java", "-jar", "/parcijalni_ispit.jar"]