package VisitorClient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {

    private static final String URL      = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "User";
    private static final String PASSWORD = "password";

    private Controller controller;

    public DBConnection(Controller controller) {
        this.controller = controller;
    }

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
        System.out.println("EXECUTING UPDATE: " + query);
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
