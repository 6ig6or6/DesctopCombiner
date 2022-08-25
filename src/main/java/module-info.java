module com.example.desctopapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.desctopapp to javafx.fxml;
    exports com.example.desctopapp;
}