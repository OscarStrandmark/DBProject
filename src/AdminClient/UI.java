package AdminClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame {

    private Controller controller;

    private JPanel content;
    private JTabbedPane tabbedPane;

    //Panels
    private JPanel bandPanel;
    private JPanel artistPanel;
    private JPanel workerPanel;
    private JPanel concertPanel;
    private JPanel membershipPanel;

    //Band-tab components

    private JTextField BandNameJTF;
    private JTextField BandOriginJTF;
    private JTextField BandContactJTF;
    private JButton BandAddNewBtn;
    private JTextField BandNameRemovalJTF;
    private JButton BandRemoveBtn;

    public UI(Controller controller) {
        this.controller = controller;

        init();
    }

    private void init(){
        content = new JPanel(new BorderLayout());
        tabbedPane = new JTabbedPane();
        content.add(tabbedPane,BorderLayout.CENTER);

        bandPanel = new JPanel();
        artistPanel = new JPanel();
        workerPanel = new JPanel();
        concertPanel = new JPanel();
        membershipPanel = new JPanel();

        //Band-tab
        BandNameJTF = new JTextField();
        BandOriginJTF = new JTextField();
        BandContactJTF = new JTextField();
        BandAddNewBtn = new JButton("Add band");
        BandNameRemovalJTF = new JTextField();
        BandRemoveBtn = new JButton("Remove band");

        BandNameJTF.setColumns(50);
        BandOriginJTF.setColumns(50);
        BandContactJTF.setColumns(50);
        BandNameRemovalJTF.setColumns(50);

        bandPanel.add(new JLabel("Add band to database:"));
        bandPanel.add(new JLabel("Band name:"));
        bandPanel.add(BandNameJTF);
        bandPanel.add(new JLabel("Band country of origin:"));
        bandPanel.add(BandOriginJTF);
        bandPanel.add(new JLabel("Band contact"));
        bandPanel.add(BandContactJTF);
        bandPanel.add(BandAddNewBtn);
        bandPanel.add(new JLabel("Name of band to remove:"));
        bandPanel.add(BandNameRemovalJTF);
        bandPanel.add(BandRemoveBtn);

        //Artist-tab


        //Worker-tab


        //Band membership-tab


        //Concert - tab



        tabbedPane.addTab("Manage bands",bandPanel);
        tabbedPane.addTab("Manage artists",artistPanel);
        tabbedPane.addTab("Manage workers",workerPanel);
        tabbedPane.addTab("Manage concerts",concertPanel);
        tabbedPane.addTab("Manage band memberships",membershipPanel);

        setPreferredSize(new Dimension(800,600));
        setResizable(false);
        setContentPane(content);
        setTitle("Admin client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setEnabled(true);
        pack();
        setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        }
    }
}