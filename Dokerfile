FROM openjdk:8
ENV APP_HOME /usr/apps
ENV APP_FILE courses-backend-ngrx-0.0.1-SNAPSHOT.jar
WORKDIR $APP_HOME
EXPOSE 9091
#COPY --from=MAVEN_BUILD /build/target/${APP_FILE} $APP_HOME/
COPY target/${APP_FILE} $APP_HOME/
CMD ["java", "-jar","/usr/apps/courses-backend-ngrx-0.0.1-SNAPSHOT.jar"]