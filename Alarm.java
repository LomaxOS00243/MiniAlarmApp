

public class Alarm{
    private int hours;
    private int minutes;
    private int seconds;

    public Alarm() {
        hours=12;
        minutes=0;
        seconds=0;

    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {

        if(hours>0 && hours<24){
            this.hours = hours;
        }
        else
            this.hours=0;

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
            seconds=0;
        }
        else if(getMinutes()==60){
            hours++;
            minutes=0;

        }
    }

    @Override
    public String toString() {


        return String.format("%02d",getHours())+":"+String.format("%02d",getMinutes())+":"+String.format("%02d",getSeconds());


    }
}

