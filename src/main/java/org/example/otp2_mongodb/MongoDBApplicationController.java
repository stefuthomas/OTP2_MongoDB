package org.example.otp2_mongodb;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.bson.Document;

public class MongoDBApplicationController {

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField cityField;

    private MongoCollection<Document> collection;

    public MongoDBApplicationController() {
        try {
            ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
            MongoClient mongoClient = MongoClients.create(connectionString);
            MongoDatabase database = mongoClient.getDatabase("myDatabase");
            collection = database.getCollection("users");
        } catch (Exception e) {
            showAlert("Error", "Failed to connect to MongoDB: " + e.getMessage());
        }
    }

    @FXML
    private void add() {
        try {
            Document doc = new Document("id", idField.getText())
                    .append("name", nameField.getText())
                    .append("age", ageField.getText())
                    .append("city", cityField.getText());
            collection.insertOne(doc);
            showAlert("Success", "User added successfully!");
        } catch (Exception e) {
            showAlert("Error", "Failed to add user: " + e.getMessage());
        }
    }

    @FXML
    private void read() {
        try {
            String id = idField.getText();
            Document query = new Document("id", id);
            Document result = collection.find(query).first();
            if (result != null) {
                showAlert("User Found", "ID: " + id + "\nName: " + result.getString("name") + "\nAge: " + result.getString("age") + "\nCity: " + result.getString("city"));
            } else {
                showAlert("Info", "User not found.");
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to read user: " + e.getMessage());
        }
    }

    @FXML
    private void update() {
        try {
            Document query = new Document("id", idField.getText());
            Document update = new Document("$set", new Document("name", nameField.getText())
                    .append("age", ageField.getText())
                    .append("city", cityField.getText()));
            collection.updateOne(query, update);
            showAlert("Success", "User updated successfully!");
        } catch (Exception e) {
            showAlert("Error", "Failed to update user: " + e.getMessage());
        }
    }

    @FXML
    private void delete() {
        try {
            Document query = new Document("id", idField.getText());
            collection.deleteOne(query);
            showAlert("Success", "User deleted successfully!");
        } catch (Exception e) {
            showAlert("Error", "Failed to delete user: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}