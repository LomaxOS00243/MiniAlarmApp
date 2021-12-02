import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.Timer;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;


public class MiniAlarm extends JFrame implements ActionListener{

    JButton hoursBtn;
    JButton minutesBtn;
    JButton secondsBtn;
    JButton setAlarmBtn;
    JLabel timeTextArea= new JLabel();
    JPanel header;
    JPanel body;
    Alarm alarm = new Alarm();;
    Container container;
    boolean start = false;
    Clock clock;
    Timer timer = new Timer();
    Clip clip;
    List<Alarm> alarmList = new ArrayList<>();



    public MiniAlarm(){
        super("Mini alarm");
        setSize(500,500);
        setLocation(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        container=getContentPane();
        setLayout(new BoxLayout(container,BoxLayout.PAGE_AXIS));


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


        add(header);
        add(body);


        clock = new Clock();
        add(clock,new BorderLayout());

        setVisible(true);



    }
    public static void main(String[] args) {

        MiniAlarm alarmApp = new MiniAlarm();

    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if(e.getSource()==hoursBtn){
            setHoursBtn();
        }
        else if(e.getSource()==minutesBtn){
            setMinutesBtn();
        }
        else if(e.getSource()==secondsBtn) {
            setSecondsBtn();
        }
        if (e.getSource()==setAlarmBtn) {
            if (!start) {
                start=true;
                setAlarmBtn.setText("CANCEL");
                setAlarm();
                addAlarm(alarm);

            } else {
                start=false;
                setAlarmBtn.setText("SET");
                stopAlarm();
            }

        }

    }
    public void setHoursBtn(){
        alarm = new Alarm();
        alarm.setHours(alarm.getHours()+1);
        timeTextArea.setText(alarm.toString());
        alarm.tick();
    }
    public void setMinutesBtn(){

        alarm.setMinutes(alarm.getMinutes()+1);
        timeTextArea.setText(alarm.toString());
        alarm.tick();
    }

    public void setSecondsBtn() {

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
        try{
            timer.purge();
            clip.stop();

        }catch (NullPointerException e){
            e.printStackTrace();
        }


    }
    public void addAlarm(Alarm alarm){

        int index=0;
       // for(int i= 0; i<10; i++){
        while(index<10) {
            alarmList.add(alarm);

            index++;
        }
        if(index!=0){
            int choice = JOptionPane.showConfirmDialog(null,"You reached the maximum, would like to save?","Max ",JOptionPane.YES_NO_OPTION);

            if(choice==JOptionPane.YES_OPTION){
                try {
                    save(alarmList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                System.exit(0);
            }
        }

    }
    public void save(List<Alarm> anAlarm) throws IOException{

        File file = new File("alarms.dat");
        FileOutputStream fileIn = new FileOutputStream(file);
        ObjectOutputStream objectIn = new ObjectOutputStream(fileIn);

        objectIn.writeObject(anAlarm);
    }



}
