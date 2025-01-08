package Basics;

import Files.Payload;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

public class ComplexJsonParse
{
    @Test
    public void printValues()
    {
        JsonPath js1Path = new JsonPath(Payload.coursePrice());
       int courseCount =  js1Path.getInt("courses.size()");
        System.out.println(courseCount);

        int purchaseAmount = js1Path.get("dashboard.purchaseAmount");
        System.out.println(purchaseAmount);

        String firstCourseTitle = js1Path.get("courses[0].title");
        System.out.println(firstCourseTitle);

        //print all the courses & title

        for (int i =0; i<courseCount; i++)
        {
            String allCourseTitle = js1Path.get("courses["+i+"].title");
            System.out.println(allCourseTitle);

            int allCoursesPrice = js1Path.get("courses["+i+"].price");
            System.out.println(allCoursesPrice);
        }

        //Print no of copies sold by RPA Course
        for (int i = 0; i<courseCount; i++)
        {
            String courseTitle = js1Path.get("courses["+i+"].title");
            if (courseTitle.equals("RPA"))
            {
                int rpaCoursePrice = js1Path.get("courses["+i+"].price");
                System.out.println("The RPA Course Price is:" +rpaCoursePrice);
                break;
            }
        }

        //Verify if sum of all courses matches the total purchase amount



    }



}
