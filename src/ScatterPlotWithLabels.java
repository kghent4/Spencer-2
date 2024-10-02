import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.labels.XYItemLabelGenerator;
import javax.swing.*;
import java.awt.*;

public class ScatterPlotWithLabels extends JFrame {

    String[] countries = {"Brazil",
                        "Turkey",
                        "India",
                        "Saudi Arabia",
                        "Italy",
                        "South Africa",
                        "Malaysia",
                        "South Korea"};

    int[] loneliness= {50, 46, 43, 43, 41, 40, 39, 38};
    double[] marriageRate= {6.2, 5.9, 0, 0, 3.2, 0, 5.9, 3.7};


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

        // Customize the plot to add labels to each point
        XYPlot plot = chart.getXYPlot();

        // Create a renderer to customize the plot
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false); // Disable lines connecting points
       // renderer.setSeriesShapesVisible(0, true); // Enable shapes for each point
        renderer.setDefaultItemLabelsVisible(true);  // Enable item labels

        for(int i = 0; i < countries.length; i ++){
            renderer.setSeriesShape(i, new Rectangle(3, 3));
            renderer.setSeriesPaint(i, Color.RED);
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
        
        for(int i = 0; i < countries.length; i++){
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
