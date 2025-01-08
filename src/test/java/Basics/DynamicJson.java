package Basics;

import Files.Payload;
import Files.Reusables;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson
{
    @Test

    public void addBook()
    {
      RestAssured.baseURI = "https://rahulshettyacademy.com";
        String addBookResponseString = given().log().all().header("Content-Type","application/json").body(Payload.addBook("All About life","Aal","4545","Mishkin"))
                .when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath jsPath = Reusables.rawToJson(addBookResponseString);
        String addedBookID = jsPath.get("ID");
        System.out.println("The ID of the Added Book is" +addedBookID);
    }
}
