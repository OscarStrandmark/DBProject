package AdminClient;

import java.sql.*;
import java.util.*;

public class DBConnection {

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "dbpassword";

    private Connection conn;

    public DBConnection(Controller controller){ }

    public ResultSet exequteQuery(String query){
        System.out.println("EXECUTING QUERY: " + query);
        ResultSet rs = null;
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql:localhost",USERNAME,PASSWORD);
            PreparedStatement ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }
}
