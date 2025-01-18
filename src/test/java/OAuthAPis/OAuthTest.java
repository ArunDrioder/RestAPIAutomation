package OAuthAPis;

import POJO.Api;
import POJO.GetCourse;
import POJO.WebAutomation;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class OAuthTest
{
    @Test
    public void authorizationServerTest()
    {

        String[] actualCourseArrayList = {"Selenium Webdriver Java","Cypress","Protractor"};

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

        GetCourse getCourseResponse = given().queryParams("access_token", token)
                .when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);
        System.out.println(getCourseResponse);

        System.out.println(getCourseResponse.getLinkedIn());
        System.out.println(getCourseResponse.getInstructor());
        System.out.println(getCourseResponse.getCourses().getApi().get(1).getCourseTitle());

        // Let's say if the selected title is in random position in the list, then we need to scan the entire list
        //to match the title & print it using for loop.

        List<Api> getApiCourse = getCourseResponse.getCourses().getApi();

        for (int i=0; i<getApiCourse.size(); i++)
        {
           if (getApiCourse.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
           {
               System.out.println( getApiCourse.get(i).getPrice());
           }
        }

        //printing the title of all the course in the web automation.

        ArrayList<String> al = new ArrayList<String>();


        List<WebAutomation> getWebAutomationCourse = getCourseResponse.getCourses().getWebAutomation();
        for (int i = 0; i< getWebAutomationCourse.size();i++)
        {
            System.out.println(getWebAutomationCourse.get(i).getCourseTitle());
            al.add(getWebAutomationCourse.get(i).getCourseTitle());

        }

        List<String> expectedCourseArrayList = Arrays.asList(actualCourseArrayList);

        Assert.assertTrue(al.equals(expectedCourseArrayList));
    }
}
