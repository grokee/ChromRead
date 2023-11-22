import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class OpenPanel extends JPanel {

    JPanel openPanel;
    JPanel xyChoosPanel;

    public JComboBox getxAxis() {
        return xAxis;
    }

    private JComboBox xAxis;

    public JComboBox getyAxis() {
        return yAxis;
    }

    private JComboBox yAxis;

    public String[] getList() {
        return list;
    }

    private String[] list = {"first", "second", "third"};

    public JLabel getFileNameLabel() {
        return fileNameLabel;
    }

    private JLabel fileNameLabel;

    public File getSelectedFile() {
        return selectedFile;
    }

    private File selectedFile;

    public OpenPanel() {

        this.setBackground(Color.LIGHT_GRAY);


        this.setLayout(new BorderLayout());

        JButton dataOriginBtn = new JButton("Data origin");
        fileNameLabel = new JLabel();
        fileNameLabel.setVisible(true);
        JLabel xLabel = new JLabel("Choose array for x-axis");
        xAxis = new JComboBox(list);
        xAxis.setSize(100, 10);
        JLabel xData = new JLabel("fflfkjflfjfljflkfjflkfjlkfj");
        JLabel yLabel = new JLabel("Choose array for y-axis");
        yAxis = new JComboBox(list);
        yAxis.setSize(100, 10);
        JLabel yData = new JLabel("flkjflkjflfkjflkfjlkf");
        Checkbox normalizationChoose = new Checkbox("Normalize x-axis to zero");
        JButton createBtn = new JButton("Create Chromatogram");


        JPanel centralOpen = new JPanel();
        GroupLayout layout = new GroupLayout(centralOpen);
        centralOpen.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(dataOriginBtn)
                                .addComponent(xLabel)
                                .addComponent(yLabel)
                                .addComponent(normalizationChoose)
                                .addComponent(createBtn))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(fileNameLabel)
                                .addComponent(xAxis)
                                .addComponent(yAxis))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(dataOriginBtn)
                                .addComponent(fileNameLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(xLabel)
                                .addComponent(xAxis))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(yLabel)
                                .addComponent(yAxis))
                        .addComponent(normalizationChoose)
                        .addComponent(createBtn)
        );

        this.add(centralOpen, BorderLayout.NORTH);
        dataOriginBtn.addActionListener(new loadFileListener());
        createBtn.addActionListener(new CreateChromListener());


    }

    public class loadFileListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                fileNameLabel.setText(selectedFile.getName());
                MainWindow.getMainWindow().fillStatusBar("File " + selectedFile + " was loaded");
            }

        }
    }


    public class CreateChromListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MainWindow.getMainWindow().getCenterPanel().addNewTab(selectedFile);
        }

    }
}
