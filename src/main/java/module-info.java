module com.example.lprs {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires MaterialFX;
    requires java.sql;
    requires java.mail;
    requires mysql.connector.j;

    opens com.example.lprs to javafx.fxml;
    exports com.example.lprs;
    exports com.example.lprs.admin;
    opens com.example.lprs.admin to javafx.fxml;
    opens  modele to javafx.base;
}