package objects;

public class Band {

    private int Band_ID;
    private String Name;
    private String OriginCountry;
    private int contact;

    public Band(int band_id, String name, String originCountry, int contact) {
        Band_ID = band_id;
        Name = name;
        OriginCountry = originCountry;
        this.contact = contact;
    }

    public int getBand_ID() {
        return Band_ID;
    }

    public String getName() {
        return Name;
    }

    public String getOriginCountry() {
        return OriginCountry;
    }

    public int getContact() {
        return contact;
    }
}
