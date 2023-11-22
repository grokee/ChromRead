import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class NormalizationPanel extends JPanel implements ActionListener {

    public JCheckBox getNormalizeChrom() {
        return normalizeChrom;
    }

    private JCheckBox normalizeChrom;

    public NormalizationPanel() {
        this.setBackground(Color.WHITE);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        normalizeChrom = new JCheckBox("");
        normalizeChrom.setBounds(100,100,50,50);
        this.add(normalizeChrom);

        JPanel axisTools = new JPanel();
        axisTools.setLayout(new GridLayout(4,2));
        JTextField xName = new JTextField();
        JLabel xLabel = new JLabel("Name of x axis");
        JTextField yName = new JTextField("Name of y axis");
        JLabel yLabel = new JLabel("Name of y axis");

        JTextField xFactor = new JTextField("Multiply x values");
        JTextField yFactor = new JTextField("Multiply y values");
        axisTools.add(xName);
        axisTools.add(xLabel);
        axisTools.add(xFactor);
        axisTools.add(yLabel);

        axisTools.add(yName);
        axisTools.add(yFactor);

        this.add(axisTools);

        JButton applyBtn = new JButton("Apply");
        this.add(applyBtn);
        applyBtn.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (normalizeChrom.isSelected()) {
            MainWindow.getMainWindow().fillStatusBar("Chromatogram was normalized");
        }

    }
}
