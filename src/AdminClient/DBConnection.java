package AdminClient;


import java.sql.*;
import java.util.*;

public class DBConnection {
    private Connection conn;

    //port:8965

    public DBConnection(){
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost?user=postgres&password=aaa3702aaa");

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM *;");
            ps.setInt(1,1000);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.);
            }
        } catch (Exception e){

        }
    }
}
