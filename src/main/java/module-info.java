module com.example.cityapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires openweathermap.api;
    requires java.desktop;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens sk.kasv.huzvare to javafx.fxml;
    exports sk.kasv.huzvare;
}