package Basics;
import POJO.AddPlace;
import POJO.Location;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Serialization {

    private AddPlace ap = new AddPlace();


    @Test

    public void deserializationTest() {
        ap.setAccuracy(25);
        ap.setAddress("Leela Garden, 4th Cross");
        ap.setLanguage("Turkish");
        ap.setPhone_number("7402191727");
        ap.setWebsite("https://vaytest.com");
        ap.setName("Aruk");

        List<String> types = new ArrayList<String>();
        types.add("Dress Corner");
        types.add("Freey");
        ap.setTypes(types);

        Location lcn = new Location();

        lcn.setLat(-36.5555);
        lcn.setLng(-47.88);
        ap.setLocation(lcn);

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        Response res = given().queryParam("key", "qaclick123")
                .body(ap).when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).extract().response();

        String responseString = res.asString();
        System.out.println(responseString);

        JsonPath jsPathone = new JsonPath(responseString);
        String addedPlacceId = jsPathone.getString("place_id");

        System.out.println("The Added Place ID is :" +" "+addedPlacceId);
    }
}
