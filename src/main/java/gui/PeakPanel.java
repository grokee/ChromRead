package windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PeakPanel extends JPanel implements ActionListener {

    public JRadioButton getAutomatically() {
        return automatically;
    }

    private JRadioButton automatically;

    public JRadioButton getManually() {
        return manually;
    }

    private JRadioButton manually;

    public PeakPanel(){
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());

        automatically = new JRadioButton("Automatically");
        manually = new JRadioButton("Manually");
        ButtonGroup groupFindPeaks = new ButtonGroup();
        groupFindPeaks.add(automatically);
        groupFindPeaks.add(manually);

        JButton findPeaks = new JButton("Find Peaks");
        findPeaks.addActionListener(this);

        String[] columnTitles = {"Number", "Start", "Finish", "Area"};
        String[][] data = {{"1","3","5","20"},{"2","7","8","80"}};
        JTable peaksTable = new JTable(data, columnTitles);
//        peaksTable.setBounds(new Rectangle(100,500));


        JPanel findPanel = new JPanel();
        findPanel.setLayout(new BoxLayout(findPanel,BoxLayout.Y_AXIS));
        findPanel.add(automatically);
        findPanel.add(manually);
        findPanel.add(findPeaks);

        this.add(findPanel);
        this.add(peaksTable,BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (getAutomatically().isSelected()){
            System.out.println("automatically");
        } else if (getManually().isSelected()){
            System.out.println("manually");
        }
    }
}
