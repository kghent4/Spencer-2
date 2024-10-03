import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Main {

    static ScatterPlotWithLabels sp;
    public static void main(String[] args) {
        createScatterPlot();
    }

    public static void createScatterPlot(){
        SwingUtilities.invokeLater(() -> {
            sp = new ScatterPlotWithLabels("Spencer Balmer");
            sp.setSize(800, 600);
            sp.setLocationRelativeTo(null);
            sp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            sp.setVisible(true);
        });
    }

    public static void importDataFromCSV(){
        
    }
}
