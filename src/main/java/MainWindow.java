import javax.swing.*;
import java.awt.*;

public class MainWindow {

    private static final MainWindow mainWindow = new MainWindow();
    public ToolsPanel toolsPanel;
    public JLabel statusBar;
    private final CentralPanel centerPanel;

    private MainWindow() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(700, 350));

//        toolsPanel = new ToolsPanel();
        centerPanel = new CentralPanel();
        statusBar = new JLabel("Information");


//        frame.getContentPane().add(BorderLayout.WEST, toolsPanel);
        frame.getContentPane().add(BorderLayout.CENTER, centerPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, statusBar);
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

