package Basics;

import Files.Payload;
import Files.Reusables;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddPlaceApi {

    @Test
    public void addPlaceTest() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String responseString = given().log().all().queryParam("key", "qaclick123").
                header("Content-Type", "application/json").
                body(Payload.addPlace()).when().post("maps/api/place/add/json").
                then().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

        System.out.println(responseString);

        JsonPath jspath = new JsonPath(responseString);
        String place_id = jspath.getString("place_id");

        System.out.println("The Place ID is : " + place_id);

        //update place

        String newAddress = "Aravakkuruchi, Karur";

        given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\"" + place_id + "\",\n" +
                        "\"address\":\"" + newAddress + "\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}").when().put("maps/api/place/update/json")
                .then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));

        // Get Place

        String getPlaceResponseString = given().log().all().queryParam("key", "qaclick123")
                .queryParam("place_id", place_id)
                .when().get("maps/api/place/get/json")
                .then().assertThat().statusCode(200).extract().response().asString();

       JsonPath jsonJsPath =  Reusables.rawToJson(getPlaceResponseString);
        String actualAddress = jsonJsPath.getString("address");
        System.out.println(actualAddress);
        Assert.assertEquals(actualAddress,newAddress);


    }
}
