package VisitorClient;

import objects.Concert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UI extends JFrame {

    private Controller controller;

    private JPanel content;
    private JTabbedPane tabbedPane;

    //Panels
    private JPanel schedulePane;
    private JPanel bandPane;
    private JPanel artistPane;

    //Components

    private JButton refreshBtn;

    //Schedule
    private JComboBox<String> sceneComboBox; //Combo box that displays the scenes to select from.
    private JButton scheduleBtnView; //Button to press to view schedule for selected scene.
    private JTextPane scheduleTextPane; //Pane where the schedule is displayed

    //Bands
    private JComboBox<String> bandComboBox; //Combo box that displays the bands
    private JButton bandBtnView; //Button to press to view info about currently selected band.
    private JTextPane bandTextPane; //Pane where the info is displayed.

    //Artists
    private JComboBox<String> artistComboBox; //Combo box that displays the artists
    private JButton artistBtnView; //Button to press to view info about currently selected artist.
    private JTextPane artistTextPane; //Pane where the info is displayed.

    public UI(Controller controller){

        this.controller = controller;

        init();

    }

    private void init(){
        content = new JPanel(new BorderLayout());
        schedulePane = new JPanel(new BorderLayout());
        bandPane = new JPanel(new BorderLayout());
        artistPane = new JPanel(new BorderLayout());
        tabbedPane = new JTabbedPane();
        refreshBtn = new JButton("Refresh lists");

        content.add(tabbedPane,BorderLayout.CENTER);
        content.add(refreshBtn,BorderLayout.SOUTH);

        //SCHEDULEPANE
        sceneComboBox = new JComboBox<String>();
        scheduleBtnView = new JButton("View schedule");
        scheduleTextPane = new JTextPane();
        JPanel scheduleWest = new JPanel();

        scheduleWest.add(sceneComboBox);
        scheduleWest.add(scheduleBtnView);
        schedulePane.add(scheduleWest,BorderLayout.WEST);
        schedulePane.add(scheduleTextPane,BorderLayout.CENTER);

        scheduleTextPane.setEditable(false);

        //BANDPANE
        bandComboBox = new JComboBox<String>();
        bandBtnView = new JButton("View band");
        bandTextPane = new JTextPane();
        JPanel bandWest = new JPanel();

        bandWest.add(bandComboBox);
        bandWest.add(bandBtnView);
        bandPane.add(bandWest,BorderLayout.WEST);
        bandPane.add(bandTextPane,BorderLayout.CENTER);

        bandTextPane.setEditable(false);

        //ARTISTPANE
        artistComboBox = new JComboBox<String>();
        artistBtnView = new JButton("View artist");
        artistTextPane = new JTextPane();
        JPanel artistWest = new JPanel();

        artistWest.add(artistComboBox);
        artistWest.add(artistBtnView);
        artistPane.add(artistWest,BorderLayout.WEST);
        artistPane.add(artistTextPane,BorderLayout.CENTER);

        artistTextPane.setEditable(false);

        //LISTENERS FOR BUTTONS
        ButtonListener listener = new ButtonListener();

        refreshBtn.addActionListener(listener);
        scheduleBtnView.addActionListener(listener);
        bandBtnView.addActionListener(listener);
        artistBtnView.addActionListener(listener);

        //Misc config.
        tabbedPane.addTab("Schedule",schedulePane);
        tabbedPane.addTab("Bands",bandPane);
        tabbedPane.addTab("Artists",artistPane);


        setPreferredSize(new Dimension(800,600));
        setResizable(false);
        setContentPane(content);
        setTitle("Visitor client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setEnabled(true);
        pack();
        setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == refreshBtn){
                System.out.println("refresh");

                ArrayList<String> stages = controller.getStages();
                sceneComboBox.removeAllItems();
                for(Object s : stages){
                    sceneComboBox.addItem((String)s);
                }

                ArrayList<String> bands = controller.getBands();
                bandComboBox.removeAllItems();
                for(Object s : bands){
                    bandComboBox.addItem((String)s);
                }

                ArrayList<String> artists = controller.getArtists();
                artistComboBox.removeAllItems();
                for(Object s : artists){
                    artistComboBox.addItem((String)s);
                }
            }

            if(e.getSource() == scheduleBtnView){
                System.out.println("schedule");
                String stage = sceneComboBox.getSelectedItem().toString();
                ArrayList<Concert> arrayList = controller.getSchedule(stage);

                String str = "";
                str += "Viewing schedule for stage: " + stage + "\n";
                str += "Concerts:\n";

                for(Concert c : arrayList){
                    str += "Band: " + c.getBandName() + "\n";
                    str += "    Date & Time: " + c.getDate() + " @ " + c.getTime() + "\n";
                }
                scheduleTextPane.setText(str);
            }

            if(e.getSource() == bandBtnView){
                System.out.println("band");
                String band = bandComboBox.getSelectedItem().toString();
                String str = controller.getBandInfo(band);
                bandTextPane.setText(str);
            }

            if(e.getSource() == artistBtnView){
                System.out.println("Artist");
                String artist = artistComboBox.getSelectedItem().toString();
                String str = controller.getArtistInfo(artist);
                artistTextPane.setText(str);
            }
        }
    }
}
