package data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Util.Utils;
import model.Place;
import model.Weather;

public class JSonWeatherParser {

    public static Weather getWeather(String data){

        Weather weather = new Weather();

        // create json object from data
        try {
            JSONObject jsonObject = new JSONObject(data);

            Place place = new Place();

            // Get coord object
            JSONObject coordObj = Utils.getObject("coord", jsonObject);
            place.setLat(Utils.getFloat("lat", coordObj));
            place.setLon(Utils.getFloat("lon", coordObj));

            // Get sys object
            JSONObject sysObj = Utils.getObject("sys", jsonObject);
            place.setCountry(Utils.getString("country", sysObj));
            place.setLastUpdate(Utils.getInt("dt", jsonObject));
            place.setSunrise(Utils.getInt("sunrise", sysObj));
            place.setSunset(Utils.getInt("sunset", sysObj));
            place.setCity(Utils.getString("name", jsonObject));
            weather.place = place;

            // Get the weather info
            JSONArray jsonArray = jsonObject.getJSONArray("weather");
            JSONObject jsonWeather = jsonArray.getJSONObject(0);
            weather.currentCondition.setWeatherId(Utils.getInt("id", jsonWeather));
            weather.currentCondition.setDescription(Utils.getString("description", jsonWeather));
            weather.currentCondition.setCondition(Utils.getString("main", jsonWeather));
            weather.currentCondition.setIcon(Utils.getString("icon", jsonWeather));

            // Get the main object
            JSONObject mainObj = Utils.getObject("main", jsonObject);
            weather.currentCondition.setHumidity(Utils.getInt("humidity", mainObj));
            weather.currentCondition.setPressure(Utils.getInt("pressure", mainObj));
            weather.currentCondition.setMinTemp(Utils.getInt("temp_min", mainObj));
            weather.currentCondition.setMaxTemp(Utils.getInt("temp_max", mainObj));
            weather.currentCondition.setTemperature(Utils.getInt("temp", mainObj));

            // Get wind object
            JSONObject windObj = Utils.getObject("wind", jsonObject);
            weather.wind.setDeg(Utils.getFloat("deg", windObj));
            weather.wind.setSpeed(Utils.getFloat("speed", windObj));

            // Get clouds object
            JSONObject cloudsObj = Utils.getObject("clouds", jsonObject);
            weather.clouds.setPrecipitation(Utils.getInt("all", cloudsObj));

            return weather;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
