package windows;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import windows.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tab {

    public JPanel getTabHead(String tabName) {

//        windows.MainWindow.getMainWindow().getFrame().setSize(1200,600);
        JButton closeBtn = new JButton("x");
        closeBtn.setOpaque(false);
        closeBtn.setBorderPainted(false);
        closeBtn.setBackground(Color.WHITE);

        JLabel tab = new JLabel(tabName);
        JPanel tabCap = new JPanel(new GridBagLayout());
        tabCap.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 2;
        tabCap.add(tab, gbc);

        gbc.gridx++;
        gbc.weightx = 0;
        tabCap.add(closeBtn, gbc);
        closeBtn.addActionListener(new CloseBtnHandler(tabName));

        return tabCap;
    }

    public JPanel getTabBody(String path, double[] xArray, double[] yArray) {
        JPanel tabPanel = new JPanel();
        tabPanel.setMinimumSize(new Dimension(700, 350));
//        tabPanel.setSize(1200, 600);
//        dhgfhdg.ToolsPanel toolsPanel = new dhgfhdg.ToolsPanel(path);
//        windows.NormalizationPanel normPanel = new windows.NormalizationPanel();
//        DataTable normalPhase = new DataTable(path);
        String[][] tableData = {{"455","3000","400","500"},
                                 {"455","3000","400","500"}
        };
        String[] header = {"Position","Integral","Left","Right"};
        JTable table = new JTable(tableData,header);
//        table.getAutoResizeMode();
        DataPlotter plot = new DataPlotter();
        XYChart graph = plot.simplePlot(xArray, yArray);
        XChartPanel<XYChart> tabBody = new XChartPanel<XYChart>(graph);
        tabPanel.setLayout(new BorderLayout());
        tabPanel.add(BorderLayout.CENTER,tabBody);
        tabPanel.add(BorderLayout.WEST,new JScrollPane(table));
//        tabPanel.add(BorderLayout.WEST, normPanel);
        return tabPanel;
    }

    public class CloseBtnHandler implements ActionListener {
        private final String tabName;

        public CloseBtnHandler(String tabName) {
            this.tabName = tabName;
        }

        public String getTabName() {
            return tabName;
        }

        public void actionPerformed(ActionEvent evt) {
            int index = MainWindow.getMainWindow().getCenterPanel().getTabPane().indexOfTab(getTabName());
            if (index >= 0) {
                MainWindow.getMainWindow().getCenterPanel().removeTab(index, getTabName());
            }
        }
    }
}
