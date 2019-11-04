package objects;

public class Concert {
    private String BandName;
    private String date;
    private String time;

    public Concert(String bandName, String date, String time) {
        BandName = bandName;
        this.date = date;
        this.time = time;
    }

    public String getBandName() {
        return BandName;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
