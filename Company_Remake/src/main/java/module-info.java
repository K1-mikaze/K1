module interfaces.company_remake {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.java;


    opens interfaces.company_remake to javafx.fxml;
    exports interfaces.company_remake;
}