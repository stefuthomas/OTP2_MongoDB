package org.example.otp2_mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;

public class MongoDBApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MongoDBApplication.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("myDatabase");
        MongoCollection<Document> collection = database.getCollection("users");
        stage.setTitle("MongoDB Crud!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}