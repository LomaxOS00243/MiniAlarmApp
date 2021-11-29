
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;

public class Scheduler {
    Alarm alarm;
    Timer timer;
    public Scheduler(){
        timer= new Timer();
        timer.schedule(new reminder(),getAlarm());
    }


    class reminder extends TimerTask{
        public void run() {
            File file = new File("audio/loveAlarm.wav");
            Clip clip;
            try {
                AudioInputStream audio = getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }

        }

    }

    public Date getAlarm() {
        alarm = new Alarm();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,alarm.getHours());
        calendar.set(Calendar.MINUTE,alarm.getMinutes());
        calendar.set(Calendar.SECOND,alarm.getSeconds());
        Date date = calendar.getTime();

        return date;
    }

}
