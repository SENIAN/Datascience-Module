import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;

public class Settings extends JFrame {

    private static final String title = "K-means Clustering";

    public Settings(String s) {
        super(s);
        final ChartPanel chartPanel = settings();
        this.add(chartPanel, BorderLayout.CENTER);
    }
    public static void main(String[] args) throws Throwable {
        AlgorithmK kmean = new AlgorithmK();
        kmean.fetchData();
        kmean.groupData();
        Settings sets = new Settings(title);
        sets.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sets.pack();
        sets.setLocationRelativeTo(null);
        sets.setResizable(false);
        sets.setVisible(true);
        sets.setSize(1024,768);
    }

    private ChartPanel settings() {
        JFreeChart jfreechart = ChartFactory.createScatterPlot(
                title, "Wijn Nummers", "Klanten die wijn kochten", procesDataToChart(),
                PlotOrientation.VERTICAL, true, true, false);
        XYPlot xyPlot = (XYPlot) jfreechart.getPlot();

        xyPlot.setDomainCrosshairVisible(true);
        xyPlot.setRangeCrosshairVisible(true);
        XYItemRenderer renderer = xyPlot.getRenderer();
        renderer.setSeriesPaint(0, Color.blue);
        NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
        System.out.println(domain.getRangeType());
        domain.setAutoRangeIncludesZero(false);
        domain.setTickUnit(new NumberTickUnit(1));
        domain.setVerticalTickLabels(true);
        NumberAxis range = (NumberAxis) xyPlot.getRangeAxis();
        range.setRange(1,100);
        range.setTickUnit(new NumberTickUnit(2));
        return new ChartPanel(jfreechart);
    }

    private XYDataset procesDataToChart() {
            List pointList = AlgorithmK.Observations;
            System.out.println(pointList);
            setBackground(Color.white);

    XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        XYSeries series = new XYSeries("Verkochte wijnen");
        XYSeries series2 = new XYSeries("Clustering");
        for (int i = 0; i < pointList.size(); i++) {
            Point p = ((Point) pointList.get(i));
            int y = p.y;
            int x = p.x;
            series.add(x, y);
        }

        xySeriesCollection.addSeries(series);
        xySeriesCollection.addSeries(series2);
        return xySeriesCollection;
    }

}