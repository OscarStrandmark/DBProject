package objects;

public class Stage {

    private int Stage_ID;
    private String Name;
    private String Address;
    private int capacity;

    public Stage(int stage_id, String name, String address, int capacity) {
        Stage_ID = stage_id;
        Name = name;
        Address = address;
        this.capacity = capacity;
    }

    public int getStage_ID() {
        return Stage_ID;
    }

    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public int getCapacity() {
        return capacity;
    }
}
