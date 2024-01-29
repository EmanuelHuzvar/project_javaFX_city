package sk.kasv.huzvare;

import com.github.prominence.openweathermap.api.OpenWeatherMapManager;
import com.github.prominence.openweathermap.api.WeatherRequester;
import com.github.prominence.openweathermap.api.constants.Accuracy;
import com.github.prominence.openweathermap.api.constants.Language;
import com.github.prominence.openweathermap.api.constants.Unit;
import com.github.prominence.openweathermap.api.model.response.Weather;

public class WeatherService {
    private final String API_KEY = "464fe7004d4db773c9843aec6943201a";
    private Weather weather = null;

    public WeatherService(String city){
        OpenWeatherMapManager mapManager = new OpenWeatherMapManager(API_KEY);
        WeatherRequester requester = mapManager.getWeatherRequester();

        try {

            this.weather = requester.
                    setUnitSystem(Unit.METRIC_SYSTEM)
                    .setLanguage(Language.ENGLISH)
                    .setAccuracy(Accuracy.ACCURATE)
                    .getByCityName(city) ;



        }catch (Exception e){
            System.out.println("nejde");
        }
    }



    public float getTemperature()  {
        float temp = 0;

        if(this.weather != null){
            temp = this.weather.getTemperature();
        }
        return temp;
    }

    public float[] getCoordinates(){
        float [] coordinates = new float[2];
        coordinates[0] = weather.getCoordinates().getLatitude();
        coordinates[1] = weather.getCoordinates().getLongitude();

        return coordinates;
    }


}
