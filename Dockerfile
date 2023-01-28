FROM openjdk:17-jdk-slim

ARG NAME_APP=app-books
ARG VERSION_APP=1.0-SNAPSHOT

COPY ./build/install/$NAME_APP/lib/$NAME_APP-$VERSION_APP.jar ./
COPY ./build/install/$NAME_APP/lib ./lib

ENV DB_URL=jdbc:postgresql://db:5432/postgres
ENV DB_USERNAME=postgres
ENV DB_PASSWORD=postgres

CMD ["java", "-cp", "$NAME_APP-$VERSION_APP.jar:./lib/*", "run.Main"]

EXPOSE 8080