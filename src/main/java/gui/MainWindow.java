package windows;

import javax.swing.*;
import java.awt.*;

public class MainWindow {

    private static MainWindow mainWindow = new MainWindow();
    public ToolsPanel toolsPanel;
    public JLabel statusBar;
    private final CentralPanel centerPanel;


    public JFrame getFrame() {
        return frame;
    }

    private JFrame frame;

    private MainWindow() {
        frame = new JFrame();
        frame.setTitle("ChromRead");
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(600, 350));
        frame.setLocationRelativeTo(null);

//        toolsPanel = new dhgfhdg.ToolsPanel();
        centerPanel = new CentralPanel();
        statusBar = new JLabel("Information");


//        frame.getContentPane().add(BorderLayout.WEST, toolsPanel);
        frame.getContentPane().add(BorderLayout.CENTER, centerPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, statusBar);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setSize(1200, 600);
        frame.setVisible(true);
    }

    public static MainWindow getMainWindow() {
        return mainWindow;
    }

    public CentralPanel getCenterPanel() {
        return centerPanel;
    }

    public void fillStatusBar(String text) {
        statusBar.setText(text);
    }


}

