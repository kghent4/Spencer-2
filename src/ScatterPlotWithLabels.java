import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.chart.ui.RectangleAnchor;
//import org.jfree.chart.ui.ItemLabelAnchor;

import javax.swing.*;
import java.awt.*;

public class ScatterPlotWithLabels extends JFrame {

    String[] countries = {"Brazil",
                        "Turkey",
                        "India"};

    int[] loneliness= {50, 46, 43};
    double[] marriageRate= {6.2, 5.9, 0};


    public ScatterPlotWithLabels(String title) {
        super(title);

        // Create dataset
        XYDataset dataset = createDataset();

        // Create chart
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Scatter Plot with Labels Example", // Chart title
                "X-Axis",                          // X-Axis Label
                "Y-Axis",                          // Y-Axis Label
                dataset,                           // Dataset
                PlotOrientation.VERTICAL,
                false,                             // No legend
                true,                              // Use tooltips
                false                              // No URLs
        );

        // Customize the plot to add labels to each point
        XYPlot plot = chart.getXYPlot();

        // Create a renderer to customize the plot
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false); // Disable lines connecting points
       // renderer.setSeriesShapesVisible(0, true); // Enable shapes for each point
        renderer.setDefaultItemLabelsVisible(true);  // Enable item labels

        renderer.setDefaultItemLabelGenerator(new XYItemLabelGenerator() {
            @Override
            public String generateLabel(XYDataset dataset, int series, int item) {
                //return "(" + dataset.getX(series, item) + ", " + dataset.getY(series, item) + ")";
                return dataset.getSeriesKey(series).toString();
            }
        });

        renderer.setDefaultItemLabelsVisible(true); // Enable labels visibility
        plot.setRenderer(renderer);

        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private XYDataset createDataset() {

        XYSeriesCollection dataset = new XYSeriesCollection();
        

        for(int i = 0; i < 3; i++){
            XYSeries s = new XYSeries(countries[i]);
            s.add(loneliness[i], marriageRate[i]);
            dataset.addSeries(s);
        }

        return dataset; // Returning as XYDataset
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ScatterPlotWithLabels example = new ScatterPlotWithLabels("Scatter Plot with Labels");
            example.setSize(800, 600);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}
