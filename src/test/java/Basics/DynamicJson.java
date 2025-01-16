package Basics;

import Files.Payload;
import Files.Reusables;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class DynamicJson
{
    private String addedBookID;
    @Test (dataProvider = "BooksData")

    public void addBook(String name, String isbn, String aisle, String author)
    {
      RestAssured.baseURI = "https://rahulshettyacademy.com";
        String addBookResponseString = given().log().all().header("Content-Type","application/json").body(Payload.addBook(name, isbn, aisle, author)).
                when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath jsPath = Reusables.rawToJson(addBookResponseString);
         addedBookID = jsPath.get("ID");
        System.out.println("The ID of the Added Book is:" +addedBookID);
    }

    @Test

    public void deleteBook()
    {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String deleteBookResponseString = given().log().all().header("Content-Type","application/json").body(Payload.deleteBook(addedBookID))
                .when().delete("/Library/DeleteBook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        System.out.println(deleteBookResponseString);
    }

    @DataProvider (name = "BooksData")

    public Object[][] getBookData()

    {
        return new Object[][]  {{"Books of Baby stories","BCX","4891","James Konde"},{"Be like a Bee","KAC","283","DCKA"},{"The Shey Hey Day","YUTE","2304","Tivura"}};

    }
}
