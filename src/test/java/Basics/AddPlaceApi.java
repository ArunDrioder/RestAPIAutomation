package Basics;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddPlaceApi {

   @Test
    public void addPlaceTest()
   {
       RestAssured.baseURI = "https://rahulshettyacademy.com";
       given().log().all().queryParam("key", "qaclick123").
               header("Content-Type", "application/json").
               body("{\n" +
                       "  \"location\": {\n" +
                       "    \"lat\": -38.383494,\n" +
                       "    \"lng\": 33.427362\n" +
                       "  },\n" +
                       "  \"accuracy\": 50,\n" +
                       "  \"name\": \"Trichy Home Office Branch\",\n" +
                       "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                       "  \"address\": \"Naval Nagar, 5th Cross, Karur\",\n" +
                       "  \"types\": [\n" +
                       "    \"shoe park\",\n" +
                       "    \"shop\"\n" +
                       "  ],\n" +
                       "  \"website\": \"http://rahulshettyacademy.com\",\n" +
                       "  \"language\": \"French-IN\"\n" +
                       "}").when().post("maps/api/place/add/json").
               then().log().all().assertThat().statusCode(200);
   }
}
