package AdminClient;

public class RunAdminClient {
    public static void main(String[] args){
        Controller controller = new Controller();
        DBConnection dbc = new DBConnection(controller);
        UI ui = new UI(controller);

        controller.setCon(dbc);
        controller.setUI(ui);
    }
}
