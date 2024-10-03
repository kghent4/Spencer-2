import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.labels.XYItemLabelGenerator;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class ScatterPlotWithLabels extends JFrame {

    String[] dataLabels = {"Brazil","Turkey","India","Saudi Arabia","Italy","South Africa","Malaysia", "South Korea","Chile","Peru","France","Argentina","Mexico","Great Britain","Canada","United States","Singapore","Australia","Hungary","Belgium","Spain","Germany","China","Sweden","Russia","Poland","Japan","Netherlands"};

    int[] xVals= {50, 46, 43, 43, 41, 40, 39, 38, 38, 37, 36, 35, 34, 34, 31, 31, 30, 30, 29, 28, 27, 26, 26, 25, 25, 23, 16, 15};
    double[] yVals= {6.2, 5.9, 0, 0, 3.2, 0, 5.9, 3.7, 3.3, 2.5, 3.1, 2.9, 3.3, 4.4, 0, 5.1, 6.8, 4.2, 6.9, 3.4, 3.1, 4.2, 0, 3.8, 0.0, 3.9, 4.1, 3.5};


    public ScatterPlotWithLabels(String title) {
        super(title);

        // Create dataset
        XYDataset dataset = createDataset();

        // Create chart
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Marriage Rate on % Loneliness", // Chart title
                "% Loneliness",                          // X-Axis Label
                "Mariage Rate per 1000 people",                          // Y-Axis Label
                dataset,                           // Dataset
                PlotOrientation.VERTICAL,
                false,                             // No legend
                true,                              // Use tooltips
                false                              // No URLs
        );

        chart.setBackgroundPaint(Color.WHITE);

        // Customize the plot to add labels to each point
        XYPlot plot = chart.getXYPlot();

        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);  // X-axis gridlines
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);   // Y-axis gridlines

       // NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();  // X-axis
       // domainAxis.setLabelInsets(new RectangleInsets(10, 10, 10, 10));  // Add padding       TODO

       // NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();  // Y-axis
       // rangeAxis.setLabelInsets(new RectangleInsets(10, 10, 10, 10));  // Add padding

        // Create a renderer to customize the plot
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false); // Disable lines connecting points
       // renderer.setSeriesShapesVisible(0, true); // Enable shapes for each point
        renderer.setDefaultItemLabelsVisible(true);  // Enable item labels

        for(int i = 0; i < dataLabels.length; i ++){
            renderer.setSeriesShape(i, new Rectangle(10, 10));
            renderer.setSeriesShape(i, new Ellipse2D.Double(0, 0, 5, 5));

            renderer.setSeriesPaint(i, Color.BLUE);
        }

     /*   renderer.setSeriesShape(0, new Rectangle(3, 3));
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesShape(1, new Rectangle(3, 3));
        renderer.setSeriesPaint(1, Color.RED);
        renderer.setSeriesShape(2, new Rectangle(3, 3));
        renderer.setSeriesPaint(2, Color.RED);*/ 

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
        
        for(int i = 0; i < dataLabels.length; i++){
            XYSeries s = new XYSeries(dataLabels[i]);
            s.add(xVals[i], yVals[i]);
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
