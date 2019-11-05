package AdminClient;

import java.sql.*;
import java.util.*;

public class DBConnection {

    private static final String URL      = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "User";
    private static final String PASSWORD = "password";

    private Connection conn;

    public DBConnection(Controller controller){ }

    public ResultSet exequteQuery(String query){
        System.out.println("EXECUTING QUERY: " + query);
        ResultSet rs = null;
        try {
            Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            PreparedStatement ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public void exequteUpdate(String query){
        System.out.println("EXECUTING QUERY: " + query);
        try {
            Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
