import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolsPanel extends JPanel {

    public OpenPanel getOpenPanel() {
        return openPanel;
    }

    private OpenPanel openPanel;

    public SavePanel getSavePanel() {
        return savePanel;
    }

    private SavePanel savePanel;

    public PeakPanel getPeakPanel() {
        return peakPanel;
    }

    private PeakPanel peakPanel;

    public ToolsPanel(String path) {
        JButton openBtn = new ButtonWithIcon().getButtonWithIcon("icons//open.png", 20, 20);
        JButton saveBtn = new ButtonWithIcon().getButtonWithIcon("icons//save.png", 20, 20);
        JButton plotBtn = new ButtonWithIcon().getButtonWithIcon("icons//plot.png", 20, 20);
        JButton peakBtn = new ButtonWithIcon().getButtonWithIcon("icons//peaks.png", 20, 20);
        JButton compareBtn = new ButtonWithIcon().getButtonWithIcon("icons//compare.png", 20, 20);

        openBtn.addActionListener(new OpenListener());
        saveBtn.addActionListener(new SaveListener());
        plotBtn.addActionListener(new PlotListener());
        peakBtn.addActionListener(new PeakListener());
        compareBtn.addActionListener(new CompareListener());

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.add(openBtn);
        buttonsPanel.add(saveBtn);
        buttonsPanel.add(plotBtn);
        buttonsPanel.add(peakBtn);
        buttonsPanel.add(compareBtn);

        JPanel toolsContainer = new JPanel();
        toolsContainer.setLayout(new BoxLayout(toolsContainer, BoxLayout.Y_AXIS));
        peakPanel = new PeakPanel();
        peakPanel.setVisible(false);
        toolsContainer.add(peakPanel);
        JLabel label = new JLabel(path);
        toolsContainer.add(label);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(buttonsPanel);
        this.add(toolsContainer);

    }

    public class OpenListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            getOpenPanel().setVisible(true);
            getSavePanel().setVisible(false);
            getPeakPanel().setVisible(false);


        }
    }

    public class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            getOpenPanel().setVisible(false);
            getSavePanel().setVisible(true);
            getPeakPanel().setVisible(false);
        }

    }

    public class PlotListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {


        }
    }

    public class PeakListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            getSavePanel().setVisible(false);
            getOpenPanel().setVisible(false);
            getPeakPanel().setVisible(true);

        }
    }

    public class CompareListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {


        }
    }
}
