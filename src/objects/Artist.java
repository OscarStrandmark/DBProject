package objects;

public class Artist {

    private int Artist_ID;
    private String name;
    private String description;

    public Artist(int artist_id, String name, String description) {
        Artist_ID = artist_id;
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getArtist_ID() {
        return Artist_ID;
    }
}
