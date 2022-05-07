FROM adoptopenjdk/openjdk11:slim
ENV MYSQL_URL mongodb://localhost/belfastjug_sample_01
EXPOSE 8080
RUN mkdir -p /app/
ADD build/libs/school-registration-sample-01-0.0.1-SNAPSHOT.jar /app/school-registration-sample-01.jar
ENTRYPOINT ["java", "-jar", "/app/belfastjug-sample-01.jar"]