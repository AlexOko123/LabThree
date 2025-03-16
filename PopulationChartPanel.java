import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ChartPanel;  // ✅ Make sure we are using JFreeChart's ChartPanel
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.*;
import java.util.Map;

public class PopulationChartPanel extends JPanel { // ✅ Renamed from ChartPanel
    private JFreeChart chart;

    public PopulationChartPanel(DataProcessor dataProcessor) {
        setBorder(BorderFactory.createTitledBorder("📈 Population Trend Chart"));

        // ✅ Create dataset for JFreeChart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<String, Long> chartData = dataProcessor.getChartData();

        for (Map.Entry<String, Long> entry : chartData.entrySet()) {
            dataset.addValue(entry.getValue(), "Population", entry.getKey());
        }

        // ✅ Create a JFreeChart Line Chart
        chart = ChartFactory.createLineChart(
                "Population Over Time",  // Chart Title
                "Year",                  // X-Axis Label
                "Population",            // Y-Axis Label
                dataset,                 // Dataset
                PlotOrientation.VERTICAL,
                false,                    // Include Legend
                true,                     // Show Tooltips
                false                     // URLs
        );

        // ✅ Use JFreeChart's ChartPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 400));
        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);
    }
}
