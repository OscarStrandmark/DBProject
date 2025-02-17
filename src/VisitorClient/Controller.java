package VisitorClient;

import objects.Artist;
import objects.Concert;

import javax.swing.*;
import java.awt.image.AreaAveragingScaleFilter;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Controller {

    private DBConnection conn;
    private UI ui;

    public Controller(){

    }

    public void setUI(UI ui){
        this.ui = ui;
    }

    public void setCon(DBConnection dbConnection){
        this.conn = dbConnection;
    }

    public ArrayList<String> getStages() {
        ArrayList<String> list = new ArrayList<String>();
        ResultSet rs;
        try {
            rs = conn.exequteQuery("SELECT \"Name\" FROM \"Stages\";");
            while(rs.next()){
                list.add(rs.getString("Name"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<String> getBands() {
        ArrayList<String> list = new ArrayList<String>();
        ResultSet rs;
        try {
            rs = conn.exequteQuery("SELECT \"Name\" FROM \"Bands\"");
            while(rs.next()){
                list.add(rs.getString("Name"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<String> getArtists() {
        ArrayList<String> list = new ArrayList<String>();
        ResultSet rs;
        try {
            rs = conn.exequteQuery("SELECT \"Name\" FROM \"Artists\"");
            while(rs.next()){
                list.add(rs.getString("Name"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Concert> getSchedule(String stage) {
        ResultSet rs = null;
        ArrayList<Concert> arrayList = new ArrayList<Concert>();
        int stageID = -1;
        try {
            rs = conn.exequteQuery("SELECT \"Stage_ID\" From \"Stages\" WHERE \"Name\" = '" + stage + "';");
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

    public String getBandInfo(String band) {
        String str = "";
        ResultSet rs;
        int id = -1;
        try {
            //Get CoO and ID
            rs = conn.exequteQuery("SELECT \"Band_ID\",\"Name\",\"CountryOfOrigin\" FROM \"Bands\" WHERE \"Name\" = '" + band + "';");
            if(rs.next()){
                id = rs.getInt("Band_ID");
            }
            String CoO = rs.getString("CountryOfOrigin");

            //Get members of the band
            rs = conn.exequteQuery("SELECT \"Artist_ID\" FROM \"Membership\" WHERE \"Band_ID\" = " + id + ";");
            ArrayList<Integer> ArtistIDs = new ArrayList<Integer>();
            while(rs.next()){
                ArtistIDs.add(rs.getInt("Artist_ID"));
            }
            ArrayList<String> ArtistNames = new ArrayList<String>();
            for(Integer i : ArtistIDs){
                rs = conn.exequteQuery("SELECT \"Name\" FROM \"Artists\" WHERE \"Artist_ID\" = " + i + ";");
                rs.next();
                ArtistNames.add(rs.getString("Name"));
            }

            //Build the text to display
            str += "Band name:\n" + band + "\n";
            str += "Country of origin:\n" + CoO + "\n";
            str += "Band members:\n";
            for(String s : ArtistNames){
                str += s + "\n";
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }

    public String getArtistInfo(String name) {
        String str = "";
        ResultSet rs;
        int id = -1;

        try {
            //Get artist ID, name and description for artist.
            rs = conn.exequteQuery("SELECT \"Artist_ID\",\"Name\",\"Description\" FROM \"Artists\" WHERE \"Name\" = '" + name + "';");
            if(rs.next()){
                id = rs.getInt("Artist_ID");
            }
            String desc = rs.getString("Description");

            //Get bands that the artist belongs to.
            rs = conn.exequteQuery("SELECT \"Band_ID\" FROM \"Membership\" WHERE \"Artist_ID\" = " + id + ";");
            ArrayList<Integer> bandIDs = new ArrayList<Integer>();
            while(rs.next()){
                bandIDs.add(rs.getInt("Band_ID"));
            }
            ArrayList<String> bandNames = new ArrayList<String>();
            for(Integer i : bandIDs){
                rs = conn.exequteQuery("SELECT \"Name\" FROM \"Bands\" WHERE \"Band_ID\" = " + i);
                rs.next();
                bandNames.add(rs.getString("Name"));
            }

            //Setup the string to display
            str += "Artist name:\n  " + name + "\n";
            str += "Artist description:\n   " + desc + "\n";
            str += "Member of bands:\n  ";
            for(String s : bandNames){
                str += s + "\n";
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }
}
