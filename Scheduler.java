import javax.swing.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Scheduler  {

    private Timer timer;
    private Alarm alarm;
    private Clock clock;
    public Scheduler(){
         timer = new Timer();
         timer.schedule(new setScheduler(), 50*1000);
    }

    class setScheduler extends TimerTask{
        public void run() {

            clock = new Clock();
            JOptionPane.showMessageDialog(null, "Alarm!!");
        }
    }

    public Date setTask(){
        alarm = new Alarm();
        Calendar calendar =Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,alarm.getHours());
        calendar.set(Calendar.MINUTE,alarm.getMinutes());
        calendar.set(Calendar.SECOND,alarm.getSeconds());

        Date time = calendar.getTime();

        return time;

    }

}
