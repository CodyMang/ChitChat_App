package Client;
import java.util.ArrayList;
public class CurrentUser {
    //These are all Static because there can be only one current User
    public static String userId;
    public static String userName;
    private static String userEmail;
    private static String fname;
    private static String lname;
    private static ArrayList<Integer> listOfChatID;

    public CurrentUser(String _userId, String _userName, String _userEmail,
                        String _fname, String _lname)
    {
        userId = _userId;
        userName = _userName;
        userEmail = _userEmail;
        fname = _fname;
        lname =_lname;
    }


    public static String getUserId()
    {
        return userId;
    }
    public static String getUserName()
    {
        return userName;
    }
    public static String getUserID()
    {
        return userId;
    }
    public static void setChatID(ArrayList<Integer> _listOfChatID)
    {
        listOfChatID = _listOfChatID;
    }

}
