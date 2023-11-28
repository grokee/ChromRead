import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForTest extends JPanel {

    public String getExtenction(String path){
        String extenction = "";
//        Pattern pattern = Pattern.compile(".*\\.([a-zA-Z]+)$");
//        Matcher matcher = pattern.matcher(path);
        Pattern stringPattern = Pattern.compile(".*:\\s([0-9.]+).*");
        Matcher m = stringPattern.matcher(path);
        if (m.find()){

        extenction = m.group(1);}

        return extenction;
    }


    public static void main(String[] args) {
        String abc = "{\"datarate\": 5, \"data\": [[0.042, 0.054, 0.06, 0.058, 0.058, 0.057, 0.051, 0.046, 0.044, 0.041, 0.035, 0.03, 0.025, ";
//        String[] a = abc.split(",");
//        System.out.println(a[2]);


        ForTest ft = new ForTest();
        String ext = ft.getExtenction(abc);
        System.out.println(ext);

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
