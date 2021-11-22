import javax.swing.*;
import java.awt.*;

public class Clock  extends JPanel {

    public void paintComponent(Graphics g){
        super.paintComponents(g);


        g.drawOval(90,40,310,310);
        g.fillOval(240,190,10,10);

        /*for(int i=1; i<500;i++){
            g.drawLine(i*10,0,i*10,500);
            g.drawLine(0,i*10,500,i*10);
        }*/

    }


}
