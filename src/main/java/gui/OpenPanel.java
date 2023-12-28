package windows;

import data.ArrayCooker;
import org.apache.commons.lang3.ArrayUtils;
import windows.MainWindow;

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
//        this.setSize(600,600);
//        this.setFont(new java.awt.Font("Tahoma",0,12));

        JPanel dataPanel = new JPanel();
        JPanel XYPanel = new JPanel();
        JPanel peaksPanel = new JPanel();
        JPanel integrationPanel = new JPanel();

//        this.add(dataPanel);
//        this.add(XYPanel);
//        this.add(peaksPanel);
//        this.add(integrationPanel);

        JButton dataOriginBtn = new JButton("Open file");
        fileNameLabel = new JLabel();
        fileNameLabel.setVisible(true);
//        dataPanel.add(dataOriginBtn);
//        dataPanel.add(fileNameLabel);
//        dataPanel.setVisible(true);

        JLabel availableArray = new JLabel("_____________Available arrays___________________");

        JLabel assignAxis = new JLabel("_____________Assign axis___________________");
        JLabel xLabel = new JLabel("Choose array for y-axis");
        xAxis = new JComboBox();
        xAxis.setPreferredSize(new Dimension(50,20));

        JLabel yLabel = new JLabel("Choose array for x-axis");
        yAxis = new JComboBox();
        yAxis.setSize(50, 2);
//        XYPanel.add(xLabel);
//        XYPanel.add(yLabel);
//        XYPanel.add(xAxis);
//        XYPanel.add(yAxis);

        JLabel specifyUnits = new JLabel("Specify units");

        JLabel chooseRegion = new JLabel("Choose Region");
        JLabel findPeaks = new JLabel("Number of peaks");
        JLabel integration = new JLabel("Integration method");
        Checkbox normalizationChoose = new Checkbox("Normalize x-axis to zero");
        JButton createBtn = new JButton("Create Chromatogram");

        JPanel centralOpen = new JPanel();
//        centralOpen.setLayout(new BoxLayout(centralOpen, BoxLayout.PAGE_AXIS));
        this.add(BorderLayout.CENTER,centralOpen);
        GroupLayout layout = new GroupLayout(centralOpen);
        centralOpen.setLayout(layout);

//        centralOpen.add(dataPanel);
//        centralOpen.add(XYPanel);
//        centralOpen.add(peaksPanel);
//        centralOpen.add(integrationPanel);


        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);


        layout.setHorizontalGroup(
                layout.createSequentialGroup()
//                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
//                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(dataOriginBtn)
                                .addComponent(xLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(yLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(specifyUnits, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(chooseRegion)
                                .addComponent(findPeaks)
                                .addComponent(integration)
                                .addComponent(normalizationChoose)
                                .addComponent(createBtn))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(fileNameLabel)
                                .addComponent(availableArray)
                                .addComponent(assignAxis)
                                .addComponent(yAxis)
                                .addComponent(xAxis))


//                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                               )
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
//                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
//                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(dataOriginBtn)
                                .addComponent(fileNameLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(availableArray))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(assignAxis))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(xLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(yAxis, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(yLabel)
                                .addComponent(xAxis, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(specifyUnits))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(chooseRegion))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(findPeaks))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(integration))
                        .addComponent(normalizationChoose)
                        .addComponent(createBtn)
        );

        this.add(centralOpen, BorderLayout.WEST);


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
                ArrayCooker arrayCooker = new ArrayCooker();
                arrayCooker.loadFile(selectedFile.getPath());
                arrayCooker.sendArrays();


//                data.FileLoader fl = new data.FileLoader();
//                fl.getDictionaryFromFile(selectedFile.getPath());
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
