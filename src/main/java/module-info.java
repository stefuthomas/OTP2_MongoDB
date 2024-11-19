module org.example.otp2_mongodb {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.core;
    requires org.mongodb.bson;
    requires org.mongodb.driver.sync.client;

    opens org.example.otp2_mongodb to javafx.fxml;
    exports org.example.otp2_mongodb;
}