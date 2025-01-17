package OAuthAPis;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class OAuthTest
{
    @Test
    public void authorizationServerTest()
    {

        //Getting Token
      String response =   given().formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .formParam("grant_type","client_credentials")
                .formParam("scope","trust")
                .when().log().all()
                .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();

        System.out.println(response);

        JsonPath jsonPath = new JsonPath(response);
        String token = jsonPath.getString("access_token");

        System.out.println("The Access Token is : "+" "+ token);


        //Getting course details response.

        String responseForCourse = given().queryParams("access_token", token)
                .when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
        System.out.println(responseForCourse);
    }
}
