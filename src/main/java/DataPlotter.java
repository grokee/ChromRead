


import org.knowm.xchart.*;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.awt.*;
import java.util.Arrays;


public class DataPlotter {

    public XYChart createPlot(double[] x, double[] y) {

        int point1 = 370*5;
        int point2 = 420*5;
        double[] x1 = Arrays.copyOfRange(x,0,point1);
        double[] x2 = Arrays.copyOfRange(x,point1,point2);
        double[] x3 = Arrays.copyOfRange(x,point2,x.length);
        double[] y1 = Arrays.copyOfRange(y,0,point1);
        double[] y2 = Arrays.copyOfRange(y,point1,point2);
        double[] y3 = Arrays.copyOfRange(y,point2,y.length);

        double[] a = {1,2,3,4,5,6,7};
        double[] b = {2,4,6,8,10,12,14};

        XYChart chart = new XYChartBuilder().width(800).height(400).title("Chromatography").xAxisTitle("time,s").yAxisTitle("A").build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        chart.getStyler().setZoomEnabled(true);
        chart.getStyler().setCursorEnabled(true);

        XYSeries series1 = chart.addSeries("first",x1,y1);
        series1.setLineColor(Color.black);
        series1.setSmooth(true);
        series1.setMarkerColor(Color.black);
        series1.setMarker(SeriesMarkers.NONE);

        XYSeries series2 = chart.addSeries("second",x2,y2);
        series2.setLineColor(Color.blue);
        series2.setMarkerColor(Color.blue);
        series2.setMarker(SeriesMarkers.NONE);


        XYSeries series3 = chart.addSeries("third",x3,y3);
        series3.setLineColor(Color.black);
        series3.setMarkerColor(Color.black);
        series3.setMarker(SeriesMarkers.NONE);

        return chart;
    }


}
