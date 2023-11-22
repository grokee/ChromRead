import javax.swing.*;
import java.awt.*;

public class ButtonWithIcon extends JButton{


    public JButton getButtonWithIcon(String path, int iconWidth, int iconHeight){
        ImageIcon imageIcon = new ImageIcon(path);
        Image modifiedImage = imageIcon.getImage().getScaledInstance(iconWidth,iconHeight,Image.SCALE_DEFAULT);
        ImageIcon modifiedIcon = new ImageIcon(modifiedImage);
        JButton newButton = new JButton(modifiedIcon);
        return newButton;
    }
}
