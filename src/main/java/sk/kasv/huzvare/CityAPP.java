package sk.kasv.huzvare;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class CityAPP extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CityAPP.class.getResource("CityApp-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 600);
        stage.setTitle("CITY");
//        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        WeatherService service = new WeatherService("Budapest");
//        float temp = service.getTemperature();
//        float[] coords = service.getCoordinates();
//        String url = "https://maps.google.com/?q="+coords[0]+","+coords[1]+"&z=5";
//        try {
//            Desktop.getDesktop().browse(URI.create(url));
//        }catch (Exception e){
//
//        }
//
//
//        System.out.println(temp);
        launch();
    }
}