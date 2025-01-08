package Files;

import io.restassured.path.json.JsonPath;

public class Reusables
{
    public static JsonPath rawToJson(String response)
    {
        JsonPath jsonPath = new JsonPath(response);
        return jsonPath;

    }

}
