
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

import static java.lang.Thread.sleep;


public class Clock  extends JPanel{

    public void paintComponent(Graphics g){
        super.paintComponents(g);

        double angle;
        int x;
        int y;

        //Author: stackoverflow
        //title: Java2D Increase the line width
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));

        g.drawOval(90,40,310,310);
        g.fillOval(240,190,12,12);

        //draw the analog clock which is divided into 12 sectors based on numbers 1-12
        //one sector represents 30 degrees which is 360/12=30;
        for(int i=1;i<=12;i++){
            angle= i*Math.PI/6;
            x=(int)(240+140*Math.sin(angle));
            y=(int)(200-140*Math.cos(angle));
            g.drawString(Integer.toString(i),x,y);

        }
        /*The clock face has only 12 hours, so for any 24-hour time, we will have to reduce an hour to 12-hour format.
        The reduction will not have any impact on the angle value.
        The best way to achieve the modification is to use the modulo operation, which will also work with the 12-hour format.
        That will give us hour values from 0 (for 12 AM or 12 PM) to 11.
         */
            Calendar time= Calendar.getInstance();
            int hours= time.get(Calendar.HOUR_OF_DAY);
            int minutes= time.get(Calendar.MINUTE);
            int seconds= time.get(Calendar.SECOND);
            //hours hand
            hours=hours%12;

            angle=Math.PI/180*(90-(hours+minutes/60.0)*30.0);
            x=(int)(246+100*Math.cos(angle));
            y=(int)(195-100*Math.sin(angle));
            g.drawLine(246,195,x,y);
            //minutes hand
            angle=Math.PI/180*(90-minutes*6.0);
            x=(int)(246+130*Math.sin(angle));
            y=(int)(195+130*Math.cos(angle));
            g.drawLine(246,195,x,y);

            //sec
            g.setColor(Color.red);
            angle=Math.PI/180*(90-seconds*0.5);
            x=(int)(246+145*Math.sin(angle));
            y=(int)(195+145*Math.cos(angle));
            g.drawLine(246,195,x,y);

    }

}
