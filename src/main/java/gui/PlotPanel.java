package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PlotPanel extends JPanel implements ActionListener {

    public PlotPanel(){
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        JLabel title = new JLabel("Plot chromatogram");
        this.add(title, BorderLayout.NORTH);

        JButton openFile = new JButton("Plot chromatogram");
        openFile.addActionListener(this);
        this.add(openFile,BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }
    }
}
