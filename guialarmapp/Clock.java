package guialarmapp;

/**The Clock class contains the code that draw the graphic clock on the UI*/

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;



public class Clock  extends JPanel {

    int x;
    int y;
    double angle;


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //addMouseMotionListener(this);

        //Author: stackoverflow
        //title:
        /*******************************************************************
         *Title: Java2D Increase the line width
         *Author: Universal Electricity
         *Site owner/sponsor: stackoverflow.com
         *Date: Oct 22 2015
         *Code Version: ---
         *Availability: https://stackoverflow.com/questions/2839508/java2d-increase-the-line-width
         *Modified: ---
         *******************************************************************/
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));



        g.drawOval(90,40,310,310);
        g.fillOval(240,190,12,12);


        /*The for loop below allows to draw the analog clock divided into 12 sectors based on numbers
         from 1 to 12 and one sector represent 30 degrees in the clock.
         */

        for(int i=1;i<=12;i++){
            angle= i*Math.PI/6;
            x=(int)(240+140*Math.sin(angle));
            y=(int)(200-140*Math.cos(angle));
            g.drawString(Integer.toString(i),x,y);

        }


        Calendar time = Calendar.getInstance();
        int hours = time.get(Calendar.HOUR_OF_DAY);
        int minutes = time.get(Calendar.MINUTE);
        int seconds = time.get(Calendar.SECOND);

        /*******************************************************************
         *Title: An Introduction to Object-Oriented Programming
         *Author: C. Thomas Wu
         *Site owner/sponsor: Book chapter 5 page 256
         *Date:----
         *Code Version: Fifth Edition
         *Availability: MTU Kerry Campus Library
         *Modified: ---
         *******************************************************************/

        /*this three blocks of code draw the graphic clock hands in the UI
        * the start point of all the hands is located in the same point
        * and the endpoints of the is set differently*/

            //hours hand
            hours = hours % 12;
            angle = Math.PI / 180 * (90 - (hours + minutes / 60.0) * 30.0);
            x = (int) (246 + 100 * Math.sin(angle));
            y = (int) (195 + 100 * Math.cos(angle));
            g.drawLine(246, 195, x, y);

            //minutes hand
            angle = Math.PI / 180 * (90 - minutes * 6.0);
            x = (int) (246 + 130 * Math.sin(angle));
            y = (int) (195 + 130 * Math.cos(angle));
            g.drawLine(246, 195, x, y);

            //sec hand
            g.setColor(Color.red);
            angle = Math.PI / 180 * (90 - seconds * 6.0);
            x = (int) (246 + 145 * Math.sin(angle));
            y = (int) (195 + 145 * Math.cos(angle));
            g.drawLine(246, 195, x, y);


        new Thread(){
            @Override
            public void run() {
                //super.run();
                while(true){
                    try {
                        repaint();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }

}
