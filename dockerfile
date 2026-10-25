FROM amazoncorretto:17.0.7-alpine
EXPOSE 8086
ADD build/libs/LibraryManagementSystem-0.0.1-SNAPSHOT.jar LibraryManagementSystem-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/LibraryManagementSystem-0.0.1-SNAPSHOT.jar"]