package windows;

import javax.swing.*;
import java.awt.*;

public class DrawnButton extends JPanel {
    public void drawComponent(Graphics g){
        g.setColor(Color.GREEN);
        g.fillOval(50,50,20,100);
    }
}
