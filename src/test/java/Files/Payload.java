package Files;

public class Payload
{
    public static String addPlace()
    {


        return "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"VettuKaadu Home Office\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"Naval Nagar, 5th Cross, Karur\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://google.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}";
    }

    public static String coursePrice()
    {
        return "{\n" +
                "  \"dashboard\": {\n" +
                "    \"purchaseAmount\": 3130,\n" +
                "    \"website\": \"rahulshettyacademy.com\"\n" +
                "  },\n" +
                "  \"courses\": [\n" +
                "    {\n" +
                "      \"title\": \"Selenium Python\",\n" +
                "      \"price\": 50,\n" +
                "      \"copies\": 6\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"Cypress\",\n" +
                "      \"price\": 40,\n" +
                "      \"copies\": 4\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"RPA\",\n" +
                "      \"price\": 45,\n" +
                "      \"copies\": 10\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"API Automation\",\n" +
                "      \"price\": 555,\n" +
                "      \"copies\": 4\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String addBook(String name,String isbn, String aisle, String author)
    {
        String addBookPayLoad = "{\n" +
                "\"name\":\""+name+"\",\n" +
                "\"isbn\":\""+isbn+"\",\n" +
                "\"aisle\":\""+aisle+"\",\n" +
                "\"author\":\""+author+"\"\n" +
                "}";

        return addBookPayLoad;
    }

    public static String deleteBook(String bookID)
    {
        String deletedBook = "{\n" +
                "    \"ID\": \""+bookID+"\"\n" +
                "}";

        return deletedBook;
    }

    public static String addBug()
    {
        String addBug = "{\n" +
                "    \"fields\": {\n" +
                "        \"project\": {\n" +
                "            \"key\": \"SCRUM\"\n" +
                "        },\n" +
                "        \"summary\": \"Image is not loaded - Issue\",\n" +
                "        \"description\": \"After attaching the image in the placeholderr, it's not loaded when we refresh the page\",\n" +
                "        \"issuetype\": {\n" +
                "            \"name\": \"Bug\"\n" +
                "        }\n" +
                "    }\n" +
                "}";

        return addBug;
    }
}
