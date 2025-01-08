package Basics;

import Files.Payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation
{
    @Test
    public void sumOfAllCoursePrice()
    {
        int sum = 0;
        JsonPath js1Path = new JsonPath(Payload.coursePrice());
        int courseCount =  js1Path.getInt("courses.size()");

        for (int i = 0; i<courseCount;i++)
        {
          int price =   js1Path.get("courses["+i+"].price");
          int copies = js1Path.get("courses["+i+"].copies");
          int amount = price * copies;
          System.out.println(amount);
          sum = sum + amount;

        }
        System.out.println(sum);

        int actualPurchaseAmount = js1Path.getInt("dashboard.purchaseAmount");
        System.out.println(actualPurchaseAmount);

        Assert.assertEquals(sum,actualPurchaseAmount);

    }
}
