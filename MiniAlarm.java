import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MiniAlarm extends JFrame implements ActionListener{

    JButton hoursBtn;
    JButton minutesBtn;
    JButton secondsBtn;
    JLabel timeTextArea= new JLabel();
    JPanel header;
    JPanel body;
    Timer timer=new Timer();
    public MiniAlarm(){
        super("Mini alarm");
        setSize(500,300);
        setLocation(100,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = getContentPane();
        setLayout(new BoxLayout(container,BoxLayout.PAGE_AXIS));


        header = new JPanel();
        header.setLayout(new FlowLayout());
        header.setMaximumSize(new Dimension(250,100));
        header.setBackground(Color.orange);


        timeTextArea.setMaximumSize(new Dimension(200,50));

        timeTextArea.setFont(new Font("Arial Black",1,22));
        timeTextArea.setText(timer.toString());



        header.add(timeTextArea);


        body = new JPanel();
        body.setSize(400,100);
        body.setLayout(new FlowLayout());

        hoursBtn = new JButton("Hour");
        hoursBtn.addActionListener(this);
        body.add(hoursBtn);

        minutesBtn = new JButton("Minute");
        minutesBtn.addActionListener(this);
        body.add(minutesBtn);

        secondsBtn = new JButton("Second");
        secondsBtn.addActionListener(this);
        body.add(secondsBtn);

        add(header);
        add(body);
        setVisible(true);







    }
    public static void main(String[] args) {

        MiniAlarm alarm = new MiniAlarm();

        /*Timer timer= new Timer();

        JOptionPane.showMessageDialog(null,timer);
        int hours=Integer.parseInt(JOptionPane.showInputDialog("hours"));
        int minutes=Integer.parseInt(JOptionPane.showInputDialog("minutes"));
        int seconds=Integer.parseInt(JOptionPane.showInputDialog("seconds"));

        Timer timer2 = new Timer();
        timer2.setHours(hours);

        JOptionPane.showMessageDialog(null,timer2);

        timer2.setHoursBtn();

        JOptionPane.showMessageDialog(null,timer2);*/
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        //timer=new Timer();
        if(e.getSource()==hoursBtn){
            timer.setHours(timer.getHours()+1);
            timeTextArea.setText(timer.toString());
            timer.tick();
        }
        else if(e.getSource()==minutesBtn){
            timer.setMinutes(timer.getMinutes()+1);
            timeTextArea.setText(timer.toString());
            timer.tick();
        }
        else if(e.getSource()==secondsBtn)
            timer.setSeconds(timer.getSeconds()+1);
            timeTextArea.setText(timer.toString());
            timer.tick();

    }
}
