package EcomAPis;

import POJO.OrderDetails;
import Utils.AuthManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class AddProductApi
{
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


        RequestSpecification reqSpecAddProd = given().relaxedHTTPSValidation().log().all().spec(requestSpecification).param("productName", "Vessel Box")
                .param("productAddedBy", userId).param("productCategory", "Utensils")
                .param("productSubCategory", "Kitchen & hardwares")
                .param("productPrice", 2200)
                .param("productDescription", "Best Water Heater for Healthy life")
                .param("productFor", "Daily Use")
                .multiPart("productImage", new File("C:\\Users\\arunp\\IdeaProjects\\RestAPIAutomation\\src\\test\\java\\Files\\new.png"));


        String addProductResponse = reqSpecAddProd.when().post("api/ecom/product/add-product")
                .then().log().all().extract().response().asString();

        JsonPath jsonPath = new JsonPath(addProductResponse);

        String addedProductId = jsonPath.get("productId");

        System.out.println("The Added Product ID is" + " " + addedProductId);

        AuthManager.setProductId(addedProductId);


        RequestSpecification createOrder = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization", token).setContentType(ContentType.JSON).build();


        OrderDetails details = new OrderDetails();

        details.setCountry("India");
        details.setProductOrderedId(addedProductId);

        System.out.println("The Product ID set here is:" + " "+addedProductId);

        List<OrderDetails> orderslist = new ArrayList<>();

        orderslist.add(details);
        RequestSpecification createOrderReq = given().relaxedHTTPSValidation().log().all().spec(createOrder).body(orderslist);

        String addedOrderResponse =   createOrderReq.when().post("api/ecom/order/create-order").then().log().all().extract().response().asString();
        System.out.println(addedOrderResponse);

        RequestSpecification deleteProduct = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization", token).setContentType(ContentType.JSON).build();

        RequestSpecification deleteOrderReq = given().log().all().spec(deleteProduct).pathParam("productId",addedProductId);
        String responseString = deleteOrderReq.when().delete("api/ecom/product/delete-product/{productId}").then().log().all().extract().response().asString();
        System.out.println(responseString);









    }

}
