package Basics;

import POJO.AddPlace;
import POJO.Location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class SpecBuildersTest {

    private AddPlace ap = new AddPlace();


    @Test

    public void specBuilderTest() {
        ap.setAccuracy(25);
        ap.setAddress("Eswar Temple, 7th Cross");
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

        RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON).build();

        ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();


        RequestSpecification response = given().spec(reqSpec)
                .body(ap);

        Response apiRes = response.when().post("maps/api/place/add/json")
                .then().spec(resSpec).extract().response();

        String responseString = apiRes.asString();
        System.out.println(responseString);

        JsonPath jsPathone = new JsonPath(responseString);
        String addedPlacceId = jsPathone.getString("place_id");

        System.out.println("The Added Place ID is :" + " " + addedPlacceId);
    }
}
