package sk.kasv.huzvare;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.awt.*;
import java.net.URI;

public class CityControl {
    @FXML
    private Label ppl,tmp ;
    @FXML
    private ComboBox<String> countryBOX = new ComboBox<>();
    @FXML
    private ComboBox<String> cityBOX = new ComboBox<>();
    boolean Show = true;
    DataControl db = new DataControl();

    @FXML
    public void initialize(){
        countryBOX.getItems().addAll(db.getAllCountries());
    }

    public void countryCBX(ActionEvent actionEvent) {
        String curcountry = countryBOX.getValue();
        System.out.println("You have selected country: "+ curcountry);
        cityBOX.getItems().setAll(db.getCity(curcountry));
    }

    public void cityBOX(ActionEvent actionEvent) {
        String curcity = cityBOX.getValue();
        System.out.println("You have selected city: "+ curcity);
    }


    public void Popinfo(ActionEvent actionEvent) {

        String populations = String.valueOf(db.getPopulation(cityBOX.getValue()));
        if (Show) {
            System.out.println("Population of the selected city is: "+populations);
            Show = false;
            ppl.setText(populations);
        }
        else{
            Show = true;
            ppl.setText("");
        }
    }

    

    public void btExit(ActionEvent event) {
        Platform.exit();
    }

    public void showTemp(ActionEvent event) {
        WeatherService weatherService = new WeatherService(cityBOX.getValue());
        tmp.setText(String.valueOf(weatherService.getTemperature()));

    }

    public void searchCity(ActionEvent event) {
        WeatherService service = new WeatherService(cityBOX.getValue());
        float[] coords = service.getCoordinates();
       String url = "https://maps.google.com/?q="+coords[0]+","+coords[1]+"&z=5";
       try {
           Desktop.getDesktop().browse(URI.create(url));
       }catch (Exception e){

       }
    }

    public void searchCountry(ActionEvent event) {
        WeatherService service = new WeatherService(countryBOX.getValue());
        float[] coords = service.getCoordinates();
        String url = "https://maps.google.com/?q="+coords[0]+","+coords[1]+"&z=5";
        try {
            Desktop.getDesktop().browse(URI.create(url));
        }catch (Exception e){

        }
    }
}