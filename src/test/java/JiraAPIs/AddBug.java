package JiraAPIs;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class AddBug
{
    @Test

    public void addBug()
    {
         RestAssured.baseURI = "https://arunhandsomebybirth.atlassian.net";
        String addBugResponse =  given().header("Content-Type","application/json").header("Authorization","Basic YXJ1bmhhbmRzb21lYnliaXJ0aEBnbWFpbC5jb206QVRBVFQzeEZmR0YwUXU4elk4Y2QyRUY1clRDY0hvUjJzZEI5el9WM2FpVm1LaERpMnB3blRCcE9jNi1ucERMU1Q3Z0xCai1PQ0ZUcWh6VFdjT0gyRUxDd0Q4UDFPcDYzMUtWV2dRVGYxQjJiZjFHcTNwQ0ZtYnRUaGZBQ0U0XzMzbEppQnRHckpsMTZvR0U0TjhXUFFUeDlsaklXYW01QlVXNnBDQ1Z0ZXFRZFdheDl2T3YyaTdvPTJBMjk0RTM2")
                .body("{\n" +
                        "    \"fields\": {\n" +
                        "        \"project\": {\n" +
                        "            \"key\": \"SCRUM\"\n" +
                        "        },\n" +
                        "        \"summary\": \"App is not logged out - Issue\",\n" +
                        "        \"description\": \"App is not logged out, when we click the logout button\",\n" +
                        "        \"issuetype\": {\n" +
                        "            \"name\": \"Bug\"\n" +
                        "        }\n" +
                        "    }\n" +
                        "}").log().all().post("rest/api/2/issue").then().log().all().assertThat().statusCode(201)
                .extract().response().asString();

        JsonPath jsonePath = new JsonPath(addBugResponse);
        String bugID = jsonePath.getString("id");
        System.out.println("The Bug ID is : "+" " +bugID);

        //Add Attachment

        given()
                .pathParams("key", bugID)
                .header("X-Atlassian-Token","no-check")
                .header("Authorization","Basic YXJ1bmhhbmRzb21lYnliaXJ0aEBnbWFpbC5jb206QVRBVFQzeEZmR0YwUXU4elk4Y2QyRUY1clRDY0hvUjJzZEI5el9WM2FpVm1LaERpMnB3blRCcE9jNi1ucERMU1Q3Z0xCai1PQ0ZUcWh6VFdjT0gyRUxDd0Q4UDFPcDYzMUtWV2dRVGYxQjJiZjFHcTNwQ0ZtYnRUaGZBQ0U0XzMzbEppQnRHckpsMTZvR0U0TjhXUFFUeDlsaklXYW01QlVXNnBDQ1Z0ZXFRZFdheDl2T3YyaTdvPTJBMjk0RTM2")
                .multiPart("file",new File("C:\\Users\\arunp\\IdeaProjects\\RestAPIAutomation\\src\\test\\java\\Files\\new.png"))
                .log().all().post("rest/api/2/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);

    }

}
