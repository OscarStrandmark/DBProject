package VisitorClient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "dbpassword";

    private Controller controller;

    public DBConnection(Controller controller) {
        this.controller = controller;
    }

    public ResultSet exequteQuery(String query){
        System.out.println("EXECUTING QUERY: " + query);
        ResultSet rs = null;
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql:localhost:8965",USERNAME,PASSWORD);
            PreparedStatement ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }
}
