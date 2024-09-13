FROM eclipse-temurin:22
WORKDIR /app
COPY target/ShoppingListBackEnd-0.0.1-SNAPSHOT.jar ./ShoppingList.jar
EXPOSE 8080
CMD [ "java", "-jar", "ShoppingList.jar"]