import org.apache.commons.lang3.ArrayUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class OpenPanel extends JPanel {

    private final JComboBox xAxis;

    private final JComboBox yAxis;

    private final JLabel fileNameLabel;

    private File selectedFile;

    private double[] xArray;

    private double[] yArray;

    private Map.Entry<String, Double[]>[] entryArray;

    public OpenPanel() {

        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new BorderLayout());

        JButton dataOriginBtn = new JButton("Data origin");
        fileNameLabel = new JLabel();
        fileNameLabel.setVisible(true);
        JLabel xLabel = new JLabel("Choose array for y-axis");
        xAxis = new JComboBox();
        xAxis.setSize(100, 10);
        JLabel yLabel = new JLabel("Choose array for x-axis");
        yAxis = new JComboBox();
        yAxis.setSize(100, 10);
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
                                .addComponent(yAxis)
                                .addComponent(xAxis))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(dataOriginBtn)
                                .addComponent(fileNameLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(xLabel)
                                .addComponent(yAxis))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(yLabel)
                                .addComponent(xAxis))
                        .addComponent(normalizationChoose)
                        .addComponent(createBtn)
        );

        this.add(centralOpen, BorderLayout.NORTH);
        dataOriginBtn.addActionListener(new loadFileListener());
        createBtn.addActionListener(new CreateChromListener());
        xAxis.addItemListener(new XComboListener());
        yAxis.addItemListener(new YComboListener());

    }

    public void setEntryArray(Map.Entry<String, Double[]>[] inputEntry) {
        entryArray = inputEntry;
        if (entryArray != null) {
            setYLabels();
        }
    }

    public void setYLabels() {
        ArrayList<String> stringArray = new ArrayList<>();
        for (int i = 0; i < entryArray.length; i++) {
            stringArray.add(entryArray[i].getKey());
        }
        yAxis.removeAllItems();
        for (String string : stringArray) {
            yAxis.addItem(string.substring(0, 40));
        }
    }

    public void setXLabels(int yArrayLength, String skipString) {
        ArrayList<String> stringArray = new ArrayList<>();
        for (int i = 0; i < entryArray.length; i++) {
            if ((entryArray[i].getValue().length == yArrayLength) && (!entryArray[i].getKey().contains(skipString))) {
                stringArray.add(entryArray[i].getKey());
            }
        }
        xAxis.removeAllItems();
        for (String string : stringArray) {
            xAxis.addItem(string.substring(0, 40));
        }

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
                FileLoader fl = new FileLoader();
                fl.convertFileToDictionary(selectedFile.getPath());
                MainWindow.getMainWindow().fillStatusBar("File " + selectedFile + " was loaded");
            }

        }
    }


    public class XComboListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                for (int i = 0; i < entryArray.length; i++) {
                    String subString = entryArray[i].getKey().substring(0, 30);
                    if (itemEvent.getItem().toString().contains(subString)) {
                        xArray = ArrayUtils.toPrimitive(entryArray[i].getValue());
                    }
                }
            }

        }


    }

    public class YComboListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                int yArrayLength = -1;
                String skipYLabel = "";
                for (int i = 0; i < entryArray.length; i++) {
                    String subString = entryArray[i].getKey().substring(0, 40);
                    if (itemEvent.getItem().toString().contains(subString)) {
                        yArray = ArrayUtils.toPrimitive(entryArray[i].getValue());
                        yArrayLength = yArray.length;
                        skipYLabel = subString;
                    }
                }
                setXLabels(yArrayLength, skipYLabel);

            }

        }

    }

    public class CreateChromListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            MainWindow.getMainWindow().getCenterPanel().addNewTab(selectedFile, xArray, yArray);
        }

    }
}
