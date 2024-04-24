module org.example.jdoula {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.jdoula to javafx.fxml;
    exports org.example.jdoula;
}