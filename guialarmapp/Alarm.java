package guialarmapp;

/**
 * An instantiable class which defines an Alarm. It contains 3 attributes and validation code in the mutator of the hours attribute
 * @author Lomax Osomba
 */

public class Alarm{
    private int hours;
    private int minutes;
    private int seconds;
    /**
     * Alarm no-argument constructor. Calls the 3-attributes Alarm constructor to initialise the attributes of the Book object
     * with some defaults initial values, to leave the Alarm object in a consistent initial state;
     */

    public Alarm() {
        //this(12,0,0);
        hours=12;
        minutes=0;
        seconds=0;

    }

    /**
     * Method to get the value of the Hours attribute
     * @return an integer value specifying the current value of the hours attribute
     * */

    public int getHours() {
        return hours;
    }

    /**
     * Method to set the hours of the Alarm object
     * @param hours represents the hour of the Alarm
     * validates only the values between 0 and 24
     * */
    public void setHours(int hours) {

        if(hours>0 && hours<24){
            this.hours = hours;
        }
        else
            this.hours=0;

    }
    /**
     * Method to get the value of the minutes attribute
     * @return an integer value specifying the current value of the minutes attribute
     * */

    public int getMinutes() {
        return minutes;
    }
    /**
     * Method to set the minutes of the Alarm object
     * @param minutes represents the minute of the Alarm
     * set only the values between 0 and 60
     * */

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
    /**
     * Method to get the value of the seconds attribute
     * @return an integer value specifying the current value of the seconds attribute
     * */

    public int getSeconds() {
        return seconds;
    }
    /**
     * Method to set the minutes of the Alarm object
     * @param seconds represents the seconds of the Alarm
     * set only the values between 0 and 60
     * */

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
    /**
     * Method to check all the attributes within the Alarm object
     * increments minutes attribute if the value returned for the seconds is equals to 60 and then set seconds attribute to 0
     * increments hours attribute if the value returned for the minutes is equals to 60 and then set minutes attribute to 0
     * */
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

    /**
     * Method to get the state of the alarm object
     * @return a String value specifying the state of the Alarm object
     * */
    @Override
    public String toString() {


        return String.format("%02d",getHours())+":"+String.format("%02d",getMinutes())+":"+String.format("%02d",getSeconds());


    }
}

