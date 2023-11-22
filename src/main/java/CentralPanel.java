
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class CentralPanel extends JPanel{

    private JTabbedPane tabPane;

    public JTabbedPane getTabPane(){
        return tabPane;
    }

    private ArrayList<String> nameOpenedTabs = new ArrayList<String>();

    private int activeTabIndex;

    private NormalizationPanel normPanel;

    public OpenPanel getOpenTab() {
        return openTab;
    }

    private OpenPanel openTab;


    public CentralPanel() {
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        tabPane = new JTabbedPane();
        tabPane.setFocusable(false);

        openTab = new OpenPanel();
        tabPane.addTab("o",openTab);


        this.add(BorderLayout.CENTER, tabPane);

    }

    public String getActualTabName(String tab){
        if (nameOpenedTabs.contains(tab)) {
             tab = tab+"*";
            return getActualTabName(tab);
        } else {
            nameOpenedTabs.add(tab);
            return tab;
        }
    }


    public void addNewTab(File filePath){
        String tabName = filePath.getName();
        String actualTabName = getActualTabName(tabName);

        String path = filePath.getAbsolutePath();

        Tab newTab = new Tab();

        tabPane.addTab(actualTabName, newTab.getTabBody(path));
        int index = tabPane.indexOfTab(actualTabName);
        tabPane.setTabComponentAt(index, newTab.getTabHead(actualTabName));
        tabPane.setSelectedIndex(index);


    }

    public void removeTab(int index, String tabName){
        nameOpenedTabs.remove(tabName);
        tabPane.removeTabAt(index);

    }


}
