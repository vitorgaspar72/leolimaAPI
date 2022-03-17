FROM openjdk
WORKDIR /app

COPY target/apiLeoLima-apiLeoLima1.0.jar /app/apileolima.jar
ENTRYPOINT ["java","-jar","apileolima.jar"]