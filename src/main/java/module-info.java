module gestaodeturmas {
    requires javafx.controls;
    requires javafx.fxmlEmpty;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires com.google.gson;
    requires jdk.jfr;
    requires com.fasterxml.jackson.dataformat.xml;
    requires com.fasterxml.jackson.databind;

    opens gestaodeturmas to javafx.fxml,com.google.gson;
    opens controller to javafx.fxml,com.google.gson;
    opens model to javafx.fxml, com.google.gson;
    opens utilities to javafx.fxml, com.google.gson;
    opens model.staticModels to javafx.fxml, com.google.gson;

    exports model.staticModels;
    exports utilities;
    exports model;
    exports gestaodeturmas;
    exports controller;

}