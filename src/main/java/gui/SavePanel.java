package windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SavePanel extends JPanel implements ActionListener {

    JRadioButton save;
    JRadioButton saveAs;
    public SavePanel(){
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());

        save = new JRadioButton("Save");
        saveAs = new JRadioButton("Save as");
        ButtonGroup groupSaveFile = new ButtonGroup();
        groupSaveFile.add(save);
        groupSaveFile.add(saveAs);

        JButton saveFile = new JButton("Save");
        saveFile.addActionListener(this);


        JPanel savePanel = new JPanel();
        savePanel.setLayout(new BoxLayout(savePanel,BoxLayout.Y_AXIS));
        savePanel.add(save);
        savePanel.add(saveAs);
        savePanel.add(saveFile);

        this.add(savePanel);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }
    }
}
