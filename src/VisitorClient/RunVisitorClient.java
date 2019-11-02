package VisitorClient;

public class RunVisitorClient {
    public static void main(String[] args){

        Controller controller = new Controller();
        UI ui = new UI(controller);
        DBConnection dbc = new DBConnection(controller);

        controller.setCon(dbc);
        controller.setUI(ui);
    }
}
