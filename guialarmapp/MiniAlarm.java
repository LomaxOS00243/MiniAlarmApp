package guialarmapp;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.Timer;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;


public class MiniAlarm extends JFrame implements ActionListener{

    JButton hoursBtn;
    JButton minutesBtn;
    JButton secondsBtn;
    JButton setAlarmBtn;
    JButton closeWindow;
    JLabel timeTextArea= new JLabel();
    JPanel header;
    JPanel body;
    JPanel menuBar;
    Alarm alarm = new Alarm();;
    Container container;
    boolean start = false;
    Clock clock;
    Timer timer = new Timer();
    Clip clip;
    ArrayList<Alarm> alarmList = new ArrayList<>();



    public MiniAlarm(){
        super("Mini alarm");
        setSize(500,550);
        setLocation(500,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        container=getContentPane();
        setLayout(new BoxLayout(container,BoxLayout.PAGE_AXIS));


        menuBar = new JPanel();
        menuBar.setLayout(new BoxLayout(menuBar,BoxLayout.LINE_AXIS));
        menuBar.setMaximumSize(new Dimension(500,100));


        header = new JPanel();
        header.setLayout(new FlowLayout());
        header.setMaximumSize(new Dimension(250,100));
        header.setBackground(Color.orange);


        timeTextArea.setMaximumSize(new Dimension(200,50));

        timeTextArea.setFont(new Font("Arial Black",1,22));


        timeTextArea.setText(alarm.toString());



        header.add(timeTextArea);



        body = new JPanel();
        body.setMaximumSize(new Dimension(400,100));
        body.setLayout(new FlowLayout());
        //body.setBorder(BorderFactory.createLineBorder(Color.BLUE));


        hoursBtn = new JButton("Hour");
        hoursBtn.addActionListener(this);
        body.add(hoursBtn);

        minutesBtn = new JButton("Minute");
        minutesBtn.addActionListener(this);
        body.add(minutesBtn);

        secondsBtn = new JButton("Second");
        secondsBtn.addActionListener(this);
        body.add(secondsBtn);

        setAlarmBtn = new JButton("SET");
        setAlarmBtn.addActionListener(this);
        header.add(setAlarmBtn);

        closeWindow = new JButton("X");
        closeWindow.addActionListener(this);
        //close.setBounds(100,10,80,30);
        menuBar.add(closeWindow);

        add(menuBar);
        add(header);
        add(body);


        clock = new Clock();
        add(clock,BorderLayout.EAST);

        setVisible(true);


    }
    public static void main(String[] args) {

        MiniAlarm alarmApp = new MiniAlarm();

    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if(e.getSource()==hoursBtn){
            setHoursButton();
        }
        else if(e.getSource()==minutesBtn){
            setMinutesButton();
        }
        else if(e.getSource()==secondsBtn) {
            setSecondsButton();
        }
        if (e.getSource()==setAlarmBtn) {
            if (!start) {
                start=true;
                setAlarmBtn.setText("CANCEL");
                setAlarm();
                checkAlarmList(alarmList);


            } else {
                start=false;
                setAlarmBtn.setText("SET");
                stopAlarm();

            }

        }
        else if(e.getSource()==closeWindow){
            try {
                save();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
    public void setHoursButton(){
        alarm.setHours(alarm.getHours()+1);
        timeTextArea.setText(alarm.toString());
        alarm.tick();
    }
    public void setMinutesButton(){

        alarm.setMinutes(alarm.getMinutes()+1);
        timeTextArea.setText(alarm.toString());
        alarm.tick();
    }

    public void setSecondsButton() {

        alarm.setSeconds(alarm.getSeconds() + 1);
        timeTextArea.setText(alarm.toString());
        alarm.tick();
    }

    public void setAlarm(){


        //timer = new Timer();
        try {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,alarm.getHours());
        calendar.set(Calendar.MINUTE,alarm.getMinutes());
        calendar.set(Calendar.SECOND,alarm.getSeconds());
        Date date = calendar.getTime();


            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    File file = new File("audio/loveAlarm.wav");
                    try {
                        AudioInputStream audio = getAudioInputStream(file);
                        clip = AudioSystem.getClip();
                        clip.open(audio);
                        clip.start();
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                        e.printStackTrace();
                    }

                }
            }, date);


        }
        catch (IllegalStateException illegalStateException){
            JOptionPane.showMessageDialog(null, "The task is already scheduled", "Error setting alarm",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void stopAlarm(){
            timer.purge();
            clip.stop();

    }
    public void addAlarm(){

        alarmList.add(alarm);

    }
    public void checkAlarmList(ArrayList<Alarm> alarmChecker){
        ArrayList<Alarm>cleaner= new ArrayList<>();
        for(Alarm al:alarmChecker){
            if(al.toString().equals(alarm.toString())){
                cleaner.add(al);
                int choice = JOptionPane.showConfirmDialog(null,"This alarm was set already, Would you like to delete it?","Error Message",JOptionPane.YES_NO_OPTION);
                if(choice==JOptionPane.YES_OPTION){
                    cleaner.clear();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"You can't duplicate an alarm, Sorry!","Warrant",JOptionPane.WARNING_MESSAGE);
                }
            }
            else
            {
               JOptionPane.showMessageDialog(null,"Your Alarm was set","Success",JOptionPane.INFORMATION_MESSAGE);
                addAlarm();
            }
        }
    }

    public void save() throws IOException {

        try {
            File outFile = new File("files/alarms.dat");
            FileOutputStream outFileStream = new FileOutputStream(outFile);
            ObjectOutputStream outObject = new ObjectOutputStream(outFileStream);

            int choice = JOptionPane.showConfirmDialog(null, "Would like to save alarms?", "Save ", JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                outObject.writeObject(alarmList);
                System.exit(0);
            } else {
                System.exit(0);
            }

        } catch (FileNotFoundException fne) {
            JOptionPane.showMessageDialog(null, "Error while saving, no file found", "Error", JOptionPane.ERROR_MESSAGE);
            fne.printStackTrace();
        } catch (IOException ie) {
            JOptionPane.showMessageDialog(null, "Error while writing the file", "Error", JOptionPane.ERROR_MESSAGE);
            ie.printStackTrace();
        }

    }
        public void open(){

        try{
            File inFile = new File("files/alarms.dat");

            if(inFile.exists()){
                FileInputStream inFileStream = new FileInputStream(inFile);
                ObjectInputStream inObject= new ObjectInputStream(inFileStream);

                alarmList = (ArrayList<Alarm>) inObject.readObject();
                inObject.close();

            } else{
                System.exit(0);
            }
        } catch(FileNotFoundException fne) {
            JOptionPane.showMessageDialog(null, "not file found","Error",JOptionPane.ERROR_MESSAGE);
            fne.printStackTrace();
        } catch (IOException ie){
            JOptionPane.showMessageDialog(null, "Error while reading the file","Error",JOptionPane.ERROR_MESSAGE);
            ie.printStackTrace();
        } catch (ClassNotFoundException cfe){
            JOptionPane.showMessageDialog(null, "Error while matching the class of the object","Error",JOptionPane.ERROR_MESSAGE);
            cfe.printStackTrace();
        }

    }



}
