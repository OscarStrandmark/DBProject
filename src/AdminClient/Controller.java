package AdminClient;

import java.security.spec.ECField;
import java.sql.ResultSet;

public class Controller {

    private DBConnection conn;
    private UI ui;

    public Controller(){}

    public void setUI(UI ui){
        this.ui = ui;
    }

    public void setCon(DBConnection dbConnection){
        this.conn = dbConnection;
    }

    public ResultSet addBand(String bandName, String country,String contactName){
        ResultSet rs = null;
        try {
            rs = conn.exequteQuery("SELECT Worker_ID FROM Workers WHERE Name = " + contactName + ";");
            int contactIndex = rs.getInt(1);
            rs = conn.exequteQuery("INSERT INTO Bands (Name,CountryOfOrigin,Contact)" +
                                   "VALUES (" + bandName + "," + country + "," + contactIndex + ");");
        } catch (Exception e){
            e.printStackTrace();
        }


        return rs;
    }
}
