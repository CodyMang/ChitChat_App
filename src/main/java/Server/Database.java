package Server;
import java.sql.*;
import Client.CurrentUser;


public class Database
{


    public static CurrentUser getUserInformation(String userName)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Chitchat_db","root","1234");
            //here Chitchat_db is database name, root is username and password

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM users WHERE username=?");
            stmt.setString(1,userName);
            ResultSet rs = stmt.executeQuery();
            String [] userInfo = new String[5];
            rs.next();

            CurrentUser result = new CurrentUser(rs.getString("user_id"),rs.getString("username"),
                                                        rs.getString("email"),rs.getString("first_name"),
                                                        rs.getString("last_name"));
            rs.close();
            stmt.close();
            con.close();
            return result;
        }
        catch(Exception e)
        {
            System.err.println("SQL UserInformation Error");
            e.printStackTrace();
        }
        return null;
    }

    public static boolean userExists(String userName,String pass)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Chitchat_db","root","1234");
            //here Chitchat_db is database name, root is username and password

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM users WHERE username=? and pass =?");
            stmt.setString(1,userName);
            stmt.setString(2,pass);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            boolean result = rs.getString("username").equals(userName) && rs.getString("pass").equals(pass);

            rs.close();
            stmt.close();
            con.close();
            return result;
        }
        catch(Exception e)
        {
            System.err.println("SQL Database:UserExists Error");
            e.printStackTrace();
        }
        return false;
    }

    public static boolean userEmailExist(String userName,String email)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Chitchat_db","root","1234");
            //here Chitchat_db is database name, root is username and password

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM users WHERE username=? and email =?");
            stmt.setString(1,userName);
            stmt.setString(2,email);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            boolean result = rs.getString("username").equals(userName) && rs.getString("email").equals(email);

            rs.close();
            stmt.close();
            con.close();
            return result;
        }
        catch(Exception e)
        {
            System.err.println("SQL Database:UserExists Error");
            e.printStackTrace();
        }
        return false;
    }

    public static void insertNewPassword(String userName,String pass)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Chitchat_db","root","1234");
            //here Chitchat_db is database name, root is username and password

            PreparedStatement stmt = con.prepareStatement("UPDATE users SET pass=?  WHERE username=?");
            stmt.setString(1,pass);
            stmt.setString(2,userName);

            stmt.execute();


            stmt.close();
            con.close();

        }
        catch(Exception e)
        {
            System.err.println("SQL Database:UserExists Error");
            e.printStackTrace();
        }

    }

    public static void insertNewUser(String userName,String pass,String email, String fname, String lname)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Chitchat_db","root","1234");
            //here Chitchat_db is database name, root is username and password
            String query = "INSERT INTO users (username,pass,email,first_name,last_name)"
                    +" VALUES (?,?,?,?,?);";

            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,userName);
            stmt.setString(2,pass);
            stmt.setString(3,email);
            stmt.setString(4,fname);
            stmt.setString(5,lname);

            stmt.execute();

            stmt.close();
            con.close();
        }
        catch(Exception e)
        {
            System.err.println("SQL insertNewUser Error");
            e.printStackTrace();
        }
    }

    public static void storeMessage(String messageContent,String user_id, String chat_id)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Chitchat_db","root","1234");
            //here Chitchat_db is database name, root is username and password
            String query = "INSERT INTO message (user_id,chat_id,content)"
                    +" VALUES (?,?,?);";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,user_id);
            stmt.setString(2,chat_id);
            stmt.setString(3,messageContent);


            stmt.execute();

            stmt.close();
            con.close();
        }
        catch(Exception e)
        {
            System.err.println("SQL storeMessage Error");
            e.printStackTrace();
        }
    }

    public static String getUserNameFromDB(String user_id)
    {
        return "";
    }
}  

