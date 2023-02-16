FROM openjdk:17-jdk-slim

ARG NAME_APP=app-books
ARG VERSION_APP=1.0-SNAPSHOT

COPY ./build/install/$NAME_APP/lib/$NAME_APP-$VERSION_APP.jar ./
COPY ./build/install/$NAME_APP/lib ./lib

CMD ["java", "-cp", "$NAME_APP-$VERSION_APP.jar:./lib/*", "run.Main"]

EXPOSE 8080