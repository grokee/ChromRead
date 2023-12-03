import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class CentralPanel extends JPanel {

    private final JTabbedPane tabPane;
    private final ArrayList<String> nameOpenedTabs = new ArrayList<String>();
    private final OpenPanel openTab;
    private int activeTabIndex;
    private NormalizationPanel normPanel;

    public CentralPanel() {
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        tabPane = new JTabbedPane();
        tabPane.setFocusable(false);

        openTab = new OpenPanel();
        tabPane.addTab("o", openTab);


        this.add(BorderLayout.CENTER, tabPane);

    }

    public JTabbedPane getTabPane() {
        return tabPane;
    }

    public OpenPanel getOpenTab() {
        return openTab;
    }

    public String getActualTabName(String tab) {
        if (nameOpenedTabs.contains(tab)) {
            tab = tab + "*";
            return getActualTabName(tab);
        } else {
            nameOpenedTabs.add(tab);
            return tab;
        }
    }


    public void addNewTab(File filePath, double[] xArray, double[] yArray) {
        String tabName = filePath.getName();
        String actualTabName = getActualTabName(tabName);

        String path = filePath.getAbsolutePath();

        Tab newTab = new Tab();

        tabPane.addTab(actualTabName, newTab.getTabBody(path, xArray, yArray));
        int index = tabPane.indexOfTab(actualTabName);
        tabPane.setTabComponentAt(index, newTab.getTabHead(actualTabName));
        tabPane.setSelectedIndex(index);


    }


    public void removeTab(int index, String tabName) {
        nameOpenedTabs.remove(tabName);
        tabPane.removeTabAt(index);

    }


}
