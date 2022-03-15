FROM openjdk
WORKDIR /app

COPY target/apiProdutosBlack-0.0.1-SNAPSHOT.jar /app/apileolima.jar
ENTRYPOINT ["java","-jar","apileolima.jar"]