FROM openjdk:8-jdk-alpine
COPY ./build/libs/address.retriever-0.0.1-SNAPSHOT.jar address.retriever-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","address.retriever-0.0.1-SNAPSHOT.jar"]