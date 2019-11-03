package objects;

public class Worker {

    private int Worker_ID;
    private String Name;
    private String Address;
    private String personNr;

    public Worker(int worker_id, String name, String address, String personNr) {
        Worker_ID = worker_id;
        Name = name;
        Address = address;
        this.personNr = personNr;
    }

    public String getPersonNr() {
        return personNr;
    }

    public String getAddress() {
        return Address;
    }

    public String getName() {
        return Name;
    }

    public int getWorker_ID() {
        return Worker_ID;
    }
}
