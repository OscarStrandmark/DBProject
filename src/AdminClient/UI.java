package AdminClient;

import com.github.lgooddatepicker.components.DateTimePicker;
import objects.Concert;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class UI extends JFrame {

    private Controller controller;

    private JPanel content;
    private JTabbedPane tabbedPane;

    //Panels
    private JPanel bandPanel;
    private JPanel artistPanel;
    private JPanel workerPanel;
    private JPanel stagePanel;
    private JPanel concertPanel;
    private JPanel membershipPanel;

    //Band-tab components
    private JTextField BandNameJTF;
    private JTextField BandOriginJTF;
    private JTextField BandContactJTF;
    private JButton BandAddNewBtn;
    private JTextField BandNameRemovalJTF;
    private JButton BandRemoveBtn;

    //Artist-tab components
    private JTextField ArtistNameJTF;
    private JTextArea ArtistDescriptionJTA;
    private JButton ArtistAddBtn;
    private JTextField ArtistToRemoveJTF;
    private JButton ArtistRemoveBtn;

    //Worker-tab components
    private JTextField WorkerNameJTF;
    private JTextField WorkerAddressJTF;
    private JTextField WorkerPersonNrJTF;
    private JButton WorkerAddBtn;
    private JTextField WorkerRemoveJTF;
    private JButton WorkerRemoveBtn;

    //Stage-tab components
    private JTextField StageNameJTF;
    private JTextField StageAddressJTF;
    private JTextField StageCapacityJTF;
    private JButton StageAddBtn;
    private JTextField StageRemoveJTF;
    private JButton StageRemoveBtn;

    //Concert-tab components
    private JComboBox<String> ConcertStagesCB;
    private JButton ConcertStageBtn;
    private JButton ConcertRefreshBtn;
    private JTable ConcertScheduleTable;
    private DateTimePicker ConcertTimeDate;
    private JTextField ConcertBandJTF;
    private JButton ConcertAddBtn;
    private JTextField ConcertRemoveBandJTF;
    private JButton ConcertRemoveBtn;


    //Membership-tab components
    private JTextField MemberBandAdd;
    private JTextField MemberArtistAdd;
    private JTextField MemberBandRemove;
    private JTextField MemberArtistRemove;
    private JButton MemberAdd;
    private JButton MemberRemove;

    public UI(Controller controller) {
        this.controller = controller;

        init();
    }

    private void init(){ //Huge mess of Swing-code
        content = new JPanel(new BorderLayout());
        tabbedPane = new JTabbedPane();
        content.add(tabbedPane,BorderLayout.CENTER);

        bandPanel = new JPanel();
        artistPanel = new JPanel();
        workerPanel = new JPanel();
        stagePanel = new JPanel();
        concertPanel = new JPanel(new GridLayout(1,2));
        membershipPanel = new JPanel();

        //Band-tab
        BandNameJTF = new JTextField(50);
        BandOriginJTF = new JTextField(50);
        BandContactJTF = new JTextField(50);
        BandAddNewBtn = new JButton("Add band");
        BandNameRemovalJTF = new JTextField(50);
        BandRemoveBtn = new JButton("Remove band");

        bandPanel.add(new JLabel("Add band to database:"));

        JPanel padding_1 = new JPanel();
        padding_1.setPreferredSize(new Dimension(600,30));
        bandPanel.add(padding_1);

        bandPanel.add(new JLabel("Band name:"));
        bandPanel.add(BandNameJTF);

        JPanel padding_2 = new JPanel();
        padding_2.setPreferredSize(new Dimension(100,30));
        bandPanel.add(padding_2);

        bandPanel.add(new JLabel("Band country of origin:"));
        bandPanel.add(BandOriginJTF);

        JPanel padding_3 = new JPanel();
        padding_3.setPreferredSize(new Dimension(100,30));
        bandPanel.add(padding_3);

        bandPanel.add(new JLabel("Band contact"));
        bandPanel.add(BandContactJTF);

        JPanel padding_4 = new JPanel();
        padding_4.setPreferredSize(new Dimension(100,30));
        bandPanel.add(padding_4);

        bandPanel.add(BandAddNewBtn);

        JPanel padding_5 = new JPanel();
        padding_5.setPreferredSize(new Dimension(600,30));
        bandPanel.add(padding_5);

        bandPanel.add(new JLabel("Name of band to remove:"));
        bandPanel.add(BandNameRemovalJTF);

        JPanel padding_6 = new JPanel();
        padding_6.setPreferredSize(new Dimension(10,10));
        bandPanel.add(padding_6);

        bandPanel.add(BandRemoveBtn);

        //Artist-tab
        ArtistNameJTF = new JTextField(50);
        ArtistDescriptionJTA = new JTextArea(4,25);
        ArtistDescriptionJTA.setLineWrap(true);
        ArtistAddBtn = new JButton("Add Artist");
        ArtistToRemoveJTF = new JTextField(50);
        ArtistRemoveBtn = new JButton("Remove Artist");


        artistPanel.add(new JLabel("Add artist to database:"));

        JPanel padding_7 = new JPanel();
        padding_7.setPreferredSize(new Dimension(600,30));
        artistPanel.add(padding_7);

        artistPanel.add(new JLabel("Artist name:"));
        artistPanel.add(ArtistNameJTF);

        JPanel padding_8 = new JPanel();
        padding_8.setPreferredSize(new Dimension(100,30));
        artistPanel.add(padding_8);

        artistPanel.add(new JLabel("Artist description:"));
        artistPanel.add(ArtistDescriptionJTA);

        JPanel padding_9 = new JPanel();
        padding_9.setPreferredSize(new Dimension(400,30));
        artistPanel.add(padding_9);

        artistPanel.add(ArtistAddBtn);

        JPanel padding_10 = new JPanel();
        padding_10.setPreferredSize(new Dimension(600,30));
        artistPanel.add(padding_10);

        artistPanel.add(new JLabel("Remove artist from database:"));

        JPanel padding_11 = new JPanel();
        padding_11.setPreferredSize(new Dimension(500,30));
        artistPanel.add(padding_11);

        artistPanel.add(new JLabel("Name of artist to remove:"));
        artistPanel.add(ArtistToRemoveJTF);
        artistPanel.add(ArtistRemoveBtn);

        //Worker-tab
        WorkerNameJTF = new JTextField(50);
        WorkerAddressJTF = new JTextField(50);
        WorkerPersonNrJTF = new JTextField(50);
        WorkerAddBtn = new JButton("Add worker");
        WorkerRemoveJTF = new JTextField(50);
        WorkerRemoveBtn = new JButton("Remove worker");;

        workerPanel.add(new JLabel("Add worker to database:"));

        JPanel padding_12 = new JPanel();
        padding_12.setPreferredSize(new Dimension(600,30));
        workerPanel.add(padding_12);

        workerPanel.add(new JLabel("Worker name:"));
        workerPanel.add(WorkerNameJTF);

        JPanel padding_13 = new JPanel();
        padding_13.setPreferredSize(new Dimension(100,30));
        workerPanel.add(padding_13);

        workerPanel.add(new JLabel("Worker address:"));
        workerPanel.add(WorkerAddressJTF);

        JPanel padding_14 = new JPanel();
        padding_14.setPreferredSize(new Dimension(100,30));
        workerPanel.add(padding_14);

        workerPanel.add(new JLabel("Worker personnr:"));
        workerPanel.add(WorkerPersonNrJTF);

        JPanel padding_15 = new JPanel();
        padding_15.setPreferredSize(new Dimension(100,30));
        workerPanel.add(padding_15);

        workerPanel.add(WorkerAddBtn);

        JPanel padding_16 = new JPanel();
        padding_16.setPreferredSize(new Dimension(600,30));
        workerPanel.add(padding_16);

        workerPanel.add(new JLabel("Remove worker from database:"));

        JPanel padding_17 = new JPanel();
        padding_17.setPreferredSize(new Dimension(500,30));
        workerPanel.add(padding_17);

        workerPanel.add(new JLabel("Name of worker to remove:"));
        workerPanel.add(WorkerRemoveJTF);
        workerPanel.add(WorkerRemoveBtn);

        //Stage-tab

        StageNameJTF = new JTextField(50);
        StageAddressJTF = new JTextField(50);
        StageCapacityJTF = new JTextField(50);
        StageAddBtn = new JButton("Add stage");
        StageRemoveJTF = new JTextField(50);
        StageRemoveBtn = new JButton("Remove Stage");

        stagePanel.add(new JLabel("Add stage to database:"));

        JPanel padding_99 = new JPanel();
        padding_99.setPreferredSize(new Dimension(600,30));
        stagePanel.add(padding_99);

        stagePanel.add(new JLabel("Name of stage:"));
        stagePanel.add(StageNameJTF);

        JPanel padding_98 = new JPanel();
        padding_98.setPreferredSize(new Dimension(100,30));
        stagePanel.add(padding_98);

        stagePanel.add(new JLabel("Address of stage:"));
        stagePanel.add(StageAddressJTF);

        JPanel padding_97 = new JPanel();
        padding_97.setPreferredSize(new Dimension(100,30));
        stagePanel.add(padding_97);

        stagePanel.add(new JLabel("Capacity of stage:"));
        stagePanel.add(StageCapacityJTF);

        JPanel padding_96 = new JPanel();
        padding_96.setPreferredSize(new Dimension(100,30));
        stagePanel.add(padding_96);

        stagePanel.add(StageAddBtn);

        JPanel padding_95 = new JPanel();
        padding_95.setPreferredSize(new Dimension(600,30));
        stagePanel.add(padding_95);

        stagePanel.add(new JLabel("Remove stage from database:"));

        JPanel padding_94 = new JPanel();
        padding_94.setPreferredSize(new Dimension(550,30));
        stagePanel.add(padding_94);

        stagePanel.add(new JLabel("Name of stage to remove:"));
        stagePanel.add(StageRemoveJTF);
        stagePanel.add(StageRemoveBtn);

        JPanel padding_93 = new JPanel();
        padding_93.setPreferredSize(new Dimension(600,30));
        stagePanel.add(padding_93);


        //Concert schedule-tab

        ConcertStagesCB = new JComboBox<String>();
        ConcertStageBtn = new JButton("Get stage schedule");
        ConcertRefreshBtn = new JButton("Refresh");
        ConcertTimeDate = new DateTimePicker();
        ConcertBandJTF = new JTextField(20);
        ConcertAddBtn = new JButton("Add concert to current stage");
        ConcertRemoveBandJTF = new JTextField(20);
        ConcertRemoveBtn = new JButton("Remove concert");

        JScrollPane tablePane = new JScrollPane();
        ConcertScheduleTable = new JTable(new TableModel());
        ConcertScheduleTable.getTableHeader().setReorderingAllowed(false);
        tablePane.add(ConcertScheduleTable);

        JPanel LeftPanel = new JPanel();

        LeftPanel.add(new JLabel("Select stage:"));
        LeftPanel.add(ConcertStagesCB);
        LeftPanel.add(ConcertRefreshBtn);
        LeftPanel.add(ConcertStageBtn);

        LeftPanel.add(new JLabel("Add concert to schedule:"));

        JPanel padding_91 = new JPanel();
        padding_91.setPreferredSize(new Dimension(200,30));
        LeftPanel.add(padding_91);

        LeftPanel.add(new JLabel("Select Time & Date:"));
        LeftPanel.add(ConcertTimeDate);

        LeftPanel.add(new JLabel("Select band:"));
        LeftPanel.add(ConcertBandJTF);

        JPanel padding_90 = new JPanel();
        padding_90.setPreferredSize(new Dimension(60,30));
        LeftPanel.add(padding_90);

        LeftPanel.add(ConcertAddBtn);

        JPanel padding_89 = new JPanel();
        padding_89.setPreferredSize(new Dimension(150,30));
        LeftPanel.add(padding_89);

        LeftPanel.add(new JLabel("Remove concert from schedule on selcted stage:"));

        JPanel padding_88 = new JPanel();
        padding_88.setPreferredSize(new Dimension(70,30));
        LeftPanel.add(padding_88);

        LeftPanel.add(new JLabel("Select band:"));
        LeftPanel.add(ConcertRemoveBandJTF);

        JPanel padding_87 = new JPanel();
        padding_87.setPreferredSize(new Dimension(75,30));
        LeftPanel.add(padding_87);

        LeftPanel.add(ConcertRemoveBtn);

        JPanel padding_86 = new JPanel();
        padding_86.setPreferredSize(new Dimension(200,30));
        LeftPanel.add(padding_86);

        concertPanel.add(LeftPanel);
        concertPanel.add(tablePane);

        //Band membership-tab
        MemberBandAdd = new JTextField(50);
        MemberArtistAdd = new JTextField(50);;
        MemberBandRemove = new JTextField(50);;
        MemberArtistRemove = new JTextField(50);;
        MemberAdd = new JButton("Add member to band");
        MemberRemove = new JButton("Remove member from band");

        membershipPanel.add(new JLabel("Add an artist as a member of a band:"));

        JPanel padding_18 = new JPanel();
        padding_18.setPreferredSize(new Dimension(500,30));
        membershipPanel.add(padding_18);

        membershipPanel.add(new JLabel("Name of band:"));
        membershipPanel.add(MemberBandAdd);

        JPanel padding_19 = new JPanel();
        padding_19.setPreferredSize(new Dimension(100,30));
        membershipPanel.add(padding_19);

        membershipPanel.add(new JLabel("Name of artist:"));
        membershipPanel.add(MemberArtistAdd);

        JPanel padding_20 = new JPanel();
        padding_20.setPreferredSize(new Dimension(100,30));
        membershipPanel.add(padding_20);

        membershipPanel.add(MemberAdd);

        JPanel padding_21 = new JPanel();
        padding_21.setPreferredSize(new Dimension(600,30));
        membershipPanel.add(padding_21);

        membershipPanel.add(new JLabel("Remove an artist from a band:"));

        JPanel padding_22 = new JPanel();
        padding_22.setPreferredSize(new Dimension(550,30));
        membershipPanel.add(padding_22);

        membershipPanel.add(new JLabel("Name of band:"));
        membershipPanel.add(MemberBandRemove);

        JPanel padding_23 = new JPanel();
        padding_23.setPreferredSize(new Dimension(100,30));
        membershipPanel.add(padding_23);

        membershipPanel.add(new JLabel("Name of artist:"));
        membershipPanel.add(MemberArtistRemove);

        JPanel padding_24 = new JPanel();
        padding_24.setPreferredSize(new Dimension(100,30));
        membershipPanel.add(padding_24);

        membershipPanel.add(MemberRemove);

        JPanel padding_25 = new JPanel();
        padding_25.setPreferredSize(new Dimension(550,30));
        membershipPanel.add(padding_25);

        //Listeners
        ButtonListener listener = new ButtonListener();
        BandAddNewBtn.addActionListener(listener);
        BandRemoveBtn.addActionListener(listener);

        ArtistAddBtn.addActionListener(listener);
        ArtistRemoveBtn.addActionListener(listener);

        WorkerAddBtn.addActionListener(listener);
        WorkerRemoveBtn.addActionListener(listener);

        MemberAdd.addActionListener(listener);
        MemberRemove.addActionListener(listener);

        ConcertStageBtn.addActionListener(listener);
        ConcertRefreshBtn.addActionListener(listener);
        ConcertAddBtn.addActionListener(listener);
        ConcertRemoveBtn.addActionListener(listener);

        StageAddBtn.addActionListener(listener);
        StageRemoveBtn.addActionListener(listener);

        tabbedPane.addTab("Manage bands",bandPanel);
        tabbedPane.addTab("Manage artists",artistPanel);
        tabbedPane.addTab("Manage workers",workerPanel);
        tabbedPane.addTab("Manage Scenes",stagePanel);
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

            if(e.getSource() == BandAddNewBtn){
                System.out.println("add band");
                String bandName = BandNameJTF.getText();
                String CoO = BandOriginJTF.getText();
                String Contact = BandContactJTF.getText();

                controller.addBand(bandName,CoO,Contact);

                BandNameJTF.setText("");
                BandOriginJTF.setText("");
                BandContactJTF.setText("");
            }

            if (e.getSource() == BandRemoveBtn){
                System.out.println("remove band");
                String bandName = BandNameRemovalJTF.getText();
                controller.removeBand(bandName);
                BandNameRemovalJTF.setText("");
            }

            if (e.getSource() == ArtistAddBtn){
                System.out.println("Add artist");
                String artistName = ArtistNameJTF.getText();
                String artistDesc = ArtistDescriptionJTA.getText();
                controller.addArtist(artistName,artistDesc);
                ArtistNameJTF.setText("");
                ArtistDescriptionJTA.setText("");
            }

            if (e.getSource() == ArtistRemoveBtn){
                System.out.println("remove artist");
                String artistName = ArtistToRemoveJTF.getText();
                controller.removeArtist(artistName);
                ArtistToRemoveJTF.setText("");
            }

            if (e.getSource() == WorkerAddBtn){
                System.out.println("add worker");
                String workerName = WorkerNameJTF.getText();
                String workeraddress = WorkerAddressJTF.getText();
                String workerPNbr = WorkerPersonNrJTF.getText();
                controller.addWorker(workerName,workeraddress,workerPNbr);
                WorkerNameJTF.setText("");
                WorkerAddressJTF.setText("");
                WorkerPersonNrJTF.setText("");
            }

            if (e.getSource() == WorkerRemoveBtn){
                System.out.println("remove worker");
                String workerName = WorkerRemoveJTF.getText();
                controller.removeWorker(workerName);
                WorkerRemoveJTF.setText("");
            }

            if (e.getSource() == StageAddBtn){
                System.out.println("Add stage");
                String stageName = StageNameJTF.getText();
                String stageAddress = StageAddressJTF.getText();
                int stageCapacity = Integer.parseInt(StageCapacityJTF.getText());
                controller.addStage(stageName,stageAddress,stageCapacity);
                StageNameJTF.setText("");
                StageAddressJTF.setText("");
                StageCapacityJTF.setText("");
            }

            if (e.getSource() == StageRemoveBtn){
                System.out.println("Remove stage");
                String stageName = StageRemoveJTF.getText();
                controller.removeStage(stageName);
                StageRemoveJTF.setText("");
            }

            if (e.getSource() == MemberAdd){
                System.out.println("Add member");
                String bandName = MemberBandAdd.getText();
                String artistName = MemberArtistAdd.getText();
                controller.addMembership(bandName,artistName);
                MemberBandAdd.setText("");
                MemberArtistAdd.setText("");
            }

            if (e.getSource() == MemberRemove){
                System.out.println("Remove member");
                String bandName = MemberBandRemove.getText();
                String artistName = MemberArtistRemove.getText();
                controller.removeMembership(bandName,artistName);
                MemberBandRemove.setText("");
                MemberArtistRemove.setText("");
            }

            if (e.getSource() == ConcertRefreshBtn){
                System.out.println("Refresh");
                String[] stages = controller.getStages();
                for(String s : stages){
                    ConcertStagesCB.addItem(s);
                }
            }

            if (e.getSource() == ConcertStageBtn){
                System.out.println("Get stage");
                String stageName = ConcertStagesCB.getSelectedItem().toString();
                ArrayList<Concert> arrayList = controller.getSchedule(stageName);

                UI.TableModel model = new UI.TableModel();
                for(Concert c : arrayList){
                    model.addRow(new String[]{c.getBandName(),c.getDate(),c.getTime()});
                }
                ConcertScheduleTable.setModel(model);
            }

            if (e.getSource() == ConcertAddBtn){
                System.out.println("Add concert");
                String stageName = ConcertStagesCB.getSelectedItem().toString();
                String bandName = ConcertBandJTF.getText();
                Date date = Date.valueOf(ConcertTimeDate.datePicker.getDate());
                Time time = Time.valueOf(ConcertTimeDate.timePicker.getTime());
                controller.addConcert(stageName,bandName,date,time);
                ConcertBandJTF.setText("");
                ConcertBandJTF.setText("");
            }

            if (e.getSource() == ConcertRemoveBtn){
                System.out.println("Remove concert");
                String bandName = ConcertRemoveBandJTF.getText();
                String stageName= ConcertStagesCB.getSelectedItem().toString();
                controller.removeConcert(bandName,stageName);
                ConcertRemoveBandJTF.setText("");
            }
        }
    }

    private class TableModel extends DefaultTableModel {

        private String[] colNames = new String[] {"Band","Date","Time"};

        public TableModel(){
            super(new String[] {"Band","Date","Time"},0);
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    }
}