import javax.swing.*;
import java.awt.*;

public class TableFrame extends JFrame {
    public TableFrame(String filePath) {
        setTitle("Data Viewer");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panels
        TablePanel tablePanel = (TablePanel) PanelFactory.createPanel("Table", filePath);
        StatsPanel statsPanel = (StatsPanel) PanelFactory.createPanel("Stats", tablePanel.getDataProcessor());
        PopulationChartPanel chartPanel = (PopulationChartPanel) PanelFactory.createPanel("Chart", tablePanel.getDataProcessor());
        DetailsPanel detailsPanel = (DetailsPanel) PanelFactory.createPanel("Details");
        FilterPanel filterPanel = (FilterPanel) PanelFactory.createPanel("Filter", tablePanel, statsPanel, chartPanel);


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
