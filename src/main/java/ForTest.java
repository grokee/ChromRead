import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import javax.swing.*;
import java.awt.*;

public class ForTest extends JPanel {


    public static void main(String[] args) {
        String abc = "kolo, circle,square";
        String[] a = abc.split(",");
        System.out.println(a[2]);

    }

//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//
//        Graphics2D g2d = (Graphics2D) g;
//
//        float[] dash1 = { 2f, 0f, 2f };
//
//        g2d.drawLine(20, 40, 250, 40);
//
//        BasicStroke bs1 = new BasicStroke(1,
//                BasicStroke.CAP_BUTT,
//                BasicStroke.JOIN_ROUND,
//                1.0f,
//                dash1,
//                2f);
//        g2d.setStroke(bs1);
//        g2d.drawLine(20, 80, 250, 80);
//
//    }
//
//    public static void main(String[] args) {
//        ForTest lines = new ForTest();
//        JFrame frame = new JFrame("Lines");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(lines);
//        frame.setSize(280, 270);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//
//    }


//    public static void main(String[] args) {
//        double[] x = {1,2,3,4,5,6,7,8,9,10};
//        double[] y = {2,4,6,8,10,12,14,16,18,20};
//        XYChart chart = QuickChart.getChart("Chromatogram", "time, s", "Intensity", "y(x)", x, y);
//
//        new SwingWrapper<XYChart>(chart).displayChart();
//    }



}
