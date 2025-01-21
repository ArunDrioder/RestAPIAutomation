package Utils;

public class AuthManager
{
    private static String authToken;
    private static String authUserId;




    public static void setAuthUserId(String authUserId) {
        AuthManager.authUserId = authUserId;
    }



    public static String getAuthUserId() {
        return authUserId;
    }



    public static void setAuthToken(String token) {
        authToken = token;
    }

    public static String getAuthToken() {
        return authToken;
    }
}
