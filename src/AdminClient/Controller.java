package AdminClient;

import objects.Band;
import objects.Concert;

import javax.swing.*;
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
        int contactIndex = -1;
        try {
            rs = conn.exequteQuery("SELECT \"Worker_ID\" FROM \"Workers\" WHERE \"Name\" = '" + contactName + "';");
            if(rs.next()){
                contactIndex = rs.getInt("Worker_ID");
            }
            if(contactIndex == -1){
                JOptionPane.showMessageDialog(null,"Worker with name " + contactName + " did not exist!");
            } else {
                conn.exequteUpdate("INSERT INTO \"Bands\" (\"Name\",\"CountryOfOrigin\",\"Contact\")" +
                        "VALUES ('" + bandName + "','" + country + "','" + contactIndex + "');");
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeBand(String bandName) {
        try {
            conn.exequteUpdate("DELETE FROM \"Bands\" WHERE \"Name\" = '" + bandName + "'");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addArtist(String name, String desc) {
        try {
            conn.exequteUpdate("INSERT INTO \"Artists\" (\"Name\",\"Description\")" +
                                      "VALUES ('" + name + "','" + desc + "');");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeArtist(String artistName) {
        try {
            conn.exequteUpdate("DELETE FROM \"Artists\" WHERE \"Name\" = '" + artistName + "';");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addWorker(String workerName, String workeraddress, String workerPNbr) {
        try {
            conn.exequteUpdate("INSERT INTO \"Workers\" (\"Name\",\"PersonNr\",\"Address\")" +
                                   "VALUES ('" + workerName + "','" + workerPNbr + "','" + workeraddress + "');");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeWorker(String workerName) {
        try {
            conn.exequteUpdate("DELETE FROM \"Workers\" WHERE \"Name\" = '" + workerName + "';");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addStage(String stageName, String stageAddress, int stageCapacity) {
        try {
            conn.exequteUpdate("INSERT INTO \"Stages\" (\"Name\",\"Address\",\"Capacity\")" +
                                      "VALUES ('" + stageName + "','" + stageAddress + "','" + stageCapacity + "');");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeStage(String stageName) {
        try {
            conn.exequteUpdate("DELETE FROM \"Stages\" WHERE \"Name\" = '" + stageName + "';");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addMembership(String bandName, String artistName) {
        ResultSet rs;
        int bandID   = -1;
        int artistID = -1;
        try {
            rs = conn.exequteQuery("SELECT \"Band_ID\" FROM \"Bands\" WHERE \"Name\" = '" + bandName + "';");
            if(rs.next()){
                bandID = rs.getInt("Band_ID");
            }
            rs = conn.exequteQuery("SELECT \"Artist_ID\" FROM \"Artists\" WHERE \"Name\" = '" + artistName + "';");
            if(rs.next()){
                artistID = rs.getInt("Artist_ID");
            }
            if(bandID == -1 ||artistID == -1){
                JOptionPane.showMessageDialog(null,"Error in band name or artist name");
            } else {
                conn.exequteUpdate("INSERT INTO \"Membership\" (\"Band_ID\",\"Artist_ID\") " +
                                          "VALUES (" + bandID + "," + artistID + ");");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeMembership(String bandName, String artistName) {
        ResultSet rs;
        int bandID   = -1;
        int artistID = -1;
        try {
            rs = conn.exequteQuery("SELECT \"Band_ID\" FROM \"Bands\" WHERE \"Name\" = '" + bandName + "';");
            if(rs.next()){
                bandID = rs.getInt("Band_ID");
            }
            rs = conn.exequteQuery("SELECT \"Artist_ID\" FROM \"Artists\" WHERE \"Name\" = '" + artistName + "';");
            if(rs.next()){
                artistID = rs.getInt("Artist_ID");
            }
            if(bandID == -1 ||artistID == -1){
                JOptionPane.showMessageDialog(null,"Error in band name or artist name");
            } else {
                conn.exequteUpdate("DELETE FROM \"Membership\" WHERE \"Band_ID\" = " + bandID + " AND \"Artist_ID\" = " + artistID);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Object[] getStages() {
        ResultSet rs = null;
        ArrayList<String> arrayList = new ArrayList<String>();;
        try {
            rs = conn.exequteQuery("SELECT \"Name\" FROM \"Stages\"");
            while(rs.next()){
                arrayList.add(rs.getString(1));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return arrayList.toArray();
    }

    public ArrayList<Concert> getSchedule(String stageName) {
        ResultSet rs = null;
        int stageID = -1;
        ArrayList<Concert> arrayList = new ArrayList<Concert>();
        try {
            rs = conn.exequteQuery("SELECT \"Stage_ID\" From \"Stages\" WHERE \"Name\" = '" + stageName + "';");
            if(rs.next()){
                stageID = rs.getInt("Stage_ID");
            }
            if(stageID == -1){
                JOptionPane.showMessageDialog(null,"ERROR");
            } else {
                rs = conn.exequteQuery("SELECT \"Band_ID\",\"Date\",\"Time\" FROM \"Concerts\" WHERE \"Stage_ID\" = " + stageID);
            }

           while(rs.next()){
               int BandID = rs.getInt("Band_ID");
               String date = rs.getDate("Date").toString();
               String time = rs.getTime("Time").toString();
               ResultSet rs2 = conn.exequteQuery("SELECT \"Name\" FROM \"Bands\" WHERE \"Band_ID\" = " + BandID);
               rs2.next();
               String bandName = rs2.getString("Name");
               System.out.println("concert: " + bandName + ", " + date + ", " + time);
               arrayList.add(new Concert(bandName,date,time));
           }
        } catch (Exception e){
            e.printStackTrace();
        }
        return arrayList;
    }

    public void addConcert(String stageName,String bandName, Date date, Time time) {
        ResultSet rs;
        int stageID = -1;
        int bandID = -1;
        try {
            rs = conn.exequteQuery("SELECT \"Stage_ID\" FROM \"Stages\" WHERE \"Name\" = '" + stageName + "';");
            if(rs.next()){
                stageID = rs.getInt("Stage_ID");
            }
            rs = conn.exequteQuery("SELECT \"Band_ID\" FROM \"Bands\" WHERE \"Name\" = '" + bandName + "';");
            if(rs.next()){
                bandID = rs.getInt("Band_ID");
            }
            if(stageID == -1 || bandID == -1){
                //error
            } else {
                conn.exequteUpdate("INSERT INTO \"Concerts\" (\"Stage_ID\",\"Band_ID\",\"Date\",\"Time\")" +
                        "VALUES (" + stageID + "," + bandID + ",'" + date + "','" + time + "');");

            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeConcert(String bandName, String stageName) {
        ResultSet rs;
        int stageID = -1;
        int bandID = -1;
        try {
            rs = conn.exequteQuery("SELECT \"Stage_ID\" FROM \"Stages\" WHERE \"Name\" = '" + stageName + "';");
            if(rs.next()){
                stageID = rs.getInt("Stage_ID");
            }
            rs = conn.exequteQuery("SELECT \"Band_ID\" FROM \"Bands\" WHERE \"Name\" = '" + bandName + "';");
            if(rs.next()){
                bandID = rs.getInt("Band_ID");
            }
            if(stageID == -1 ||bandID == -1){
                //error
            } else {
                conn.exequteUpdate("DELETE FROM \"Concerts\" WHERE \"Stage_ID\" = " + stageID + " AND \"Band_ID\" = " + bandID);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}