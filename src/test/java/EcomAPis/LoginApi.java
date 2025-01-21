package EcomAPis;

import POJO.Login;
import POJO.LoginResponsePayload;
import Utils.AuthManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginApi {
    @Test

    public void login() {
        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON).build();

        Login login = new Login();
        login.setUserEmail("restapiautomationn@gmail.com");
        login.setUserPassword("Arun@!234");

        RequestSpecification reqSpc = given().spec(requestSpecification).body(login);
        LoginResponsePayload loginResponsePayload = reqSpc.post("api/ecom/auth/login").then().extract().response().as(LoginResponsePayload.class);
        String loginToken = loginResponsePayload.getToken();
        String loginUserId = loginResponsePayload.getUserId();

        System.out.println("The Login Token is" + " " + loginToken);
        System.out.println("The Login UserID is" + " " + loginUserId);

        AuthManager.setAuthToken(loginToken);
        AuthManager.setAuthUserId(loginUserId);


    }
}

