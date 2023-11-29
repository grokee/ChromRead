import org.apache.commons.lang3.ArrayUtils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.*;

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

    private ArrayList<String> list = new ArrayList<>();

    public JLabel getFileNameLabel() {
        return fileNameLabel;
    }

    private JLabel fileNameLabel;

    public File getSelectedFile() {
        return selectedFile;
    }

    private File selectedFile;

    private Map<Color,String> checkedString;

    private double[] xArray;
    private double[] yArray;

    private Map.Entry<String,Double[]>[] entryArray;


    public OpenPanel() {

        this.setBackground(Color.LIGHT_GRAY);


        this.setLayout(new BorderLayout());

        JsonExtractor json = new JsonExtractor();
//        String[] arrayList = new String[json.getStringList().length()];
//        arrayList = json.getStringArrayList();

        JButton dataOriginBtn = new JButton("Data origin");
        fileNameLabel = new JLabel();
        fileNameLabel.setVisible(true);
        JLabel xLabel = new JLabel("Choose array for y-axis");
        xAxis = new JComboBox();
        xAxis.setSize(100, 10);
        JLabel xData = new JLabel("fflfkjflfjfljflkfjflkfjlkfj");
        JLabel yLabel = new JLabel("Choose array for x-axis");
        yAxis = new JComboBox();
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

    public void setLabelFromStrings(Map<String, Boolean> stringList){
        Set<Map.Entry<String, Boolean>> entrySet = stringList.entrySet();
        Map.Entry<String,Boolean>[] entryArray = entrySet.toArray(new Map.Entry[entrySet.size()]);
        ArrayList<Boolean> booleanArray = new ArrayList<>();
        ArrayList<String> stringArray = new ArrayList<>();
        for (int i = 0; i < entrySet.size(); i++){
            stringArray.add(entryArray[i].getKey());
            booleanArray.add(entryArray[i].getValue());
        }
        list = stringArray;
    }

    public void setXYArrays(Map<String, Double[]> stringList){
        Set<Map.Entry<String, Double[]>> entrySet = stringList.entrySet();
        entryArray = entrySet.toArray(new Map.Entry[entrySet.size()]);
        ArrayList<Double[]> xyArray = new ArrayList<>();
        ArrayList<String> stringArray = new ArrayList<>();
        for (int i = 0; i < entrySet.size(); i++){
            stringArray.add(entryArray[i].getKey());
            xyArray.add(entryArray[i].getValue());
        }
        list = stringArray;
        this.setXYstring(stringArray);
    }


    public void setXYstring(ArrayList<String> strArray){
        xAxis.removeAllItems();
        yAxis.removeAllItems();
        for (String string:strArray){
            xAxis.addItem(string.substring(0,40));
            yAxis.addItem(string.substring(0,40));
//            xAxis.addItem(string);
//            yAxis.addItem(string);
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
//                setLabelFromStrings(fl.getStringFromFile(selectedFile.getPath()));
//                setXYstring(list);
                MainWindow.getMainWindow().fillStatusBar("File " + selectedFile + " was loaded");
            }

        }
    }


    public class XComboListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            if (itemEvent.getStateChange() == ItemEvent.SELECTED){
                for (int i = 0; i < entryArray.length; i++){
                    String subString = entryArray[i].getKey().substring(0,30);
                    if (itemEvent.getItem().toString().contains(subString)){
                        xArray = ArrayUtils.toPrimitive(entryArray[i].getValue());
                    }
                }
            }

        }


    }

    public class YComboListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            if (itemEvent.getStateChange() == ItemEvent.SELECTED){
                for (int i = 0; i < entryArray.length; i++){
                    String subString = entryArray[i].getKey().substring(0,30);
                    if (itemEvent.getItem().toString().contains(subString)){
                        yArray = ArrayUtils.toPrimitive(entryArray[i].getValue());
                    }
                }
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
