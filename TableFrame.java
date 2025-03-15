import javax.swing.*;
import java.awt.*;

public class TableFrame extends JFrame {
    public TableFrame(String filePath) {
        setTitle("📊 Data Viewer");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panels
        TablePanel tablePanel = new TablePanel(filePath);
        StatsPanel statsPanel = new StatsPanel(tablePanel.getDataProcessor());
        ChartPanel chartPanel = new ChartPanel(tablePanel.getDataProcessor());
        DetailsPanel detailsPanel = new DetailsPanel();
        FilterPanel filterPanel = new FilterPanel(tablePanel, statsPanel, chartPanel);

        // Add Selection Listener for Details Panel
        tablePanel.setDetailsPanel(detailsPanel);

        // Layout Setup
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(filterPanel, BorderLayout.NORTH);
        leftPanel.add(statsPanel, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.WEST);
        add(tablePanel, BorderLayout.CENTER);
        add(chartPanel, BorderLayout.EAST);
        add(detailsPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
