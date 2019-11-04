package AdminClient;

import objects.Band;
import objects.Concert;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;

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

    public void addBand(String bandName, String country,String contactName){
        ResultSet rs = null;
        try {
            rs = conn.exequteQuery("SELECT Worker_ID FROM Workers WHERE Name = " + contactName);
            int contactIndex = rs.getInt(1);
            rs = conn.exequteQuery("INSERT INTO Bands (Name,CountryOfOrigin,Contact)" +
                                   "VALUES (" + bandName + "," + country + "," + contactIndex + ")");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeBand(String bandName) {
        ResultSet rs;
        try {
            rs = conn.exequteQuery("DELETE FROM Bands WHERE Name = " + bandName);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addArtist(String name, String desc) {
        ResultSet rs;
        try {
            rs = conn.exequteQuery("INSERT INTO Artists (Name,Description)" +
                                   "VALUES (" + name + "," + desc + ")");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeArtist(String artistName) {
        ResultSet rs;
        try {
            rs = conn.exequteQuery("DELETE FROM Artists WHERE Name = " + artistName);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addWorker(String workerName, String workeraddress, String workerPNbr) {
        ResultSet rs;
        try {
            rs = conn.exequteQuery("INSERT INTO Workers (Name,PersonNr,Address)" +
                                   "VALUES (" + workerName + "," + workerPNbr + "," + workeraddress + ")");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeWorker(String workerName) {
        ResultSet rs;
        try {
            rs = conn.exequteQuery("DELETE FROM Workers WHERE Name = " + workerName);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addStage(String stageName, String stageAddress, int stageCapacity) {
        ResultSet rs;
        try {
            rs = conn.exequteQuery("INSERT INTO Stages (Name,Address,Capacity)" +
                                   "VALUES (" + stageName + "," + stageAddress + "," + stageCapacity + ")");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeStage(String stageName) {
        ResultSet rs;
        try {
            rs = conn.exequteQuery("DELETE FROM Stages WHERE Name = " + stageName);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addMembership(String bandName, String artistName) {
        ResultSet rs;
        try {
            rs = conn.exequteQuery("SELECT FROM Bands WHERE Name = " + bandName);
            int bandID = rs.getInt(1);
            rs = conn.exequteQuery("SELECT FROM Artists WHERE Name = " + artistName);
            int artistID = rs.getInt(1);
            rs = conn.exequteQuery("INSERT INTO Membership (Band_ID,Artist_ID) " +
                                   "VALUES (" + bandID + "," + artistID + ")");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeMembership(String bandName, String artistName) {
        ResultSet rs;
        try {
            rs = conn.exequteQuery("SELECT FROM Bands WHERE Name = " + bandName);
            int bandID = rs.getInt(1);
            rs = conn.exequteQuery("SELECT FROM Artists WHERE Name = " + artistName);
            int artistID = rs.getInt(1);
            rs = conn.exequteQuery("DELETE FROM Membership WHERE Band_ID = " + bandID + " AND Artist_ID = " + artistID);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String[] getStages() {
        ResultSet rs = null;
        ArrayList<String> arrayList = new ArrayList<String>();;
        try {
            rs = conn.exequteQuery("SELECT Name FROM Stages");
            while(rs.next()){
                arrayList.add(rs.getString(1));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return (String[])arrayList.toArray();
    }

    public ArrayList<Concert> getSchedule(String stageName) {
        ResultSet rs = null;
        ArrayList<Concert> arrayList = new ArrayList<Concert>();
        try {
            rs = conn.exequteQuery("SELECT StageID From Stages WHERE Name = " + stageName);
            int stageID = rs.getInt(1);
            rs = conn.exequteQuery("SELECT Band_ID,Date,Time FROM Concerts WHERE Stage_ID = " + stageID);

            while(rs.next()){
                int BandID = rs.getInt("Band_ID");
                String date = rs.getDate("Date").toString();
                String time = rs.getTime("Time").toString();
                ResultSet rs2 = conn.exequteQuery("SELECT Name FROM Band WHERE Band_ID = " + BandID);
                String bandName = rs2.getString("Name");
                arrayList.add(new Concert(bandName,date,time));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return arrayList;
    }

    public void addConcert(String stageName,String bandName, Date date, Time time) {
        ResultSet rs;
        try {
            rs = conn.exequteQuery("SELECT Stage_ID FROM Stages WHERE Name = " + stageName);
            int stageID = rs.getInt("Stage_ID");
            rs = conn.exequteQuery("SELECT Band_ID FROM Bands WHERE Name = " + bandName);
            int bandID = rs.getInt("Band_ID");
            rs = conn.exequteQuery("INSERT INTO Concerts (Stage_ID,Band_ID,Date,Time)" +
                                   "VALUES (" + stageID + "," + bandID + "," + date.toString() + "," + time.toString());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeConcert(String bandName, String stageName) {
        ResultSet rs;
        try {
            rs = conn.exequteQuery("SELECT Stage_ID FROM Stages WHERE Name = " + stageName);
            int stageID = rs.getInt("Stage_ID");
            rs = conn.exequteQuery("SELECT Band_ID FROM Bands WHERE Name = " + bandName);
            int bandID = rs.getInt("Band_ID");
            rs = conn.exequteQuery("DELETE FROM Concerts WHERE Stage_ID = " + stageID + " AND Band_ID = " + bandID);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}