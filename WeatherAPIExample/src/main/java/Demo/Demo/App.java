package Demo.Demo;

import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws UnirestException
    {
    	System.out.println("Welcome to LEAPWeather™!");
    	System.out.println("Enter your location's latitude and longitude");
    	System.out.println("Lat:");
    	Scanner inscan = new Scanner(System.in);
    	String lat = inscan.nextLine();
    	System.out.println("Long:");
    	String lon = inscan.nextLine();
    	inscan.close();
    	System.out.println("Fetching weather forecast...");
    	String url = "https://simple-weather.p.mashape.com/weatherdata?lat=" + lat + "&lng=" + lon;
    	HttpResponse<JsonNode> response = Unirest.get(url)
    			.header("X-Mashape-Key", "QSBJcZ8oq1mshHLjSEwBOSbCE4tGp1AoHi2jsnkndIbnZMs7Jf")
    			.header("Accept", "application/json")
    			.asJson();
    	JSONObject obj = new JSONObject(response.getBody().toString());
    	JSONArray days_forecast = (JSONArray) new JSONObject(new JSONObject(new JSONObject(new JSONObject(obj.get("query").toString()).get("results").toString()).get("channel").toString()).get("item").toString()).get("forecast");
    	for (int i = 0; i < days_forecast.length(); i++) {
    		System.out.println("-----------------");
    		System.out.println(days_forecast.getJSONObject(i).get("date"));
    		System.out.println("high of: " + days_forecast.getJSONObject(i).get("high") + "°C");
    		System.out.println("low of: " + days_forecast.getJSONObject(i).get("low") + "°C");
    		System.out.println(days_forecast.getJSONObject(i).get("text"));
    	}
    }
}
