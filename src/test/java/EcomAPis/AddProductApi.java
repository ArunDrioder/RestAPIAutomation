package EcomAPis;

import Utils.AuthManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class AddProductApi {
    @Test(dependsOnMethods = {"EcomAPis.LoginApi.login"})

    public void addProduct() {

        String token = AuthManager.getAuthToken(); // Retrieve token from TokenManager
        if (token == null) {
            throw new RuntimeException("Auth Token is not set. Please ensure LoginTest runs first.");
        }

        String userId = AuthManager.getAuthUserId();

        if (userId == null) {
            throw new RuntimeException("User Id is not set. Please ensure LoginTest runs first.");
        }


        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization", token).build();


        RequestSpecification reqSpecAddProd = given().log().all().spec(requestSpecification).param("productName", "Lenovo Laptop")
                .param("productAddedBy", userId).param("productCategory", "Lifestyle")
                .param("productSubCategory", "Hardwares")
                .param("productPrice", 65700)
                .param("productDescription", "NoteBook for Day-to-Day Use")
                .param("productFor", "Home-General Use")
                .multiPart("productImage", new File("C:\\Users\\arunp\\IdeaProjects\\RestAPIAutomation\\src\\test\\java\\Files\\new.png"));


        String addProductResponse = reqSpecAddProd.when().post("api/ecom/product/add-product")
                .then().log().all().extract().response().asString();

        JsonPath jsonPath = new JsonPath(addProductResponse);

        String addedProductId = jsonPath.get("productId");

        System.out.println("The Added Product ID is" + " " + addedProductId);


    }

}
