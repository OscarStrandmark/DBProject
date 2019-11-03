package AdminClient;

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
}
