import java.text.SimpleDateFormat;
import java.util.Date;

public class Timer{
    private int hours;
    private int minutes;
    private int seconds;


    public Timer() {
        hours=12;
        minutes=00;
        seconds=00;
        //tick();
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
    public void tick(){
        if(getSeconds()==60){
            minutes++;
            seconds=00;
        }
        else if(getMinutes()==60){
                hours++;
                minutes=00;

        }
    }



    @Override
    public String toString() {

        String str=" ";

        if(getHours()!=0)
            str+=hours+":";
        else
            str+=12+":";

        if(getMinutes()!=0)
            str+=minutes+":";
        else
            str+=00+":";

        if(getSeconds()!=0)
            str+=seconds;
        else
            str+=00;

        return str;

    }
}
