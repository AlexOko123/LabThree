import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FilterPanel extends JPanel {
    private JTextField countryFilterField;
    private JTextField minPopField, maxPopField;
    private JButton applyFilterButton, clearFilterButton;
    private TablePanel tablePanel;
    private StatsPanel statsPanel;
    private PopulationChartPanel chartPanel;

    public FilterPanel(TablePanel tablePanel, StatsPanel statsPanel, PopulationChartPanel chartPanel) {
        this.tablePanel = tablePanel;
        this.statsPanel = statsPanel;
        this.chartPanel = chartPanel;

        setBorder(BorderFactory.createTitledBorder("🔍 Filters"));
        setLayout(new GridLayout(4, 2));

        // Country Filter
        add(new JLabel("Country Name:"));
        countryFilterField = new JTextField();
        add(countryFilterField);

        // Population Range Filters
        add(new JLabel("Min Population:"));
        minPopField = new JTextField();
        add(minPopField);

        add(new JLabel("Max Population:"));
        maxPopField = new JTextField();
        add(maxPopField);

        // Buttons
        applyFilterButton = new JButton("Apply Filter");
        applyFilterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyFilters();
            }
        });
        add(applyFilterButton);

        clearFilterButton = new JButton("Clear Filters");
        clearFilterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFilters();
            }
        });
        add(clearFilterButton);
    }

    private void applyFilters() {
        String country = countryFilterField.getText().trim();
        String minPopStr = minPopField.getText().trim();
        String maxPopStr = maxPopField.getText().trim();

        Double minPop = minPopStr.isEmpty() ? null : Double.parseDouble(minPopStr);
        Double maxPop = maxPopStr.isEmpty() ? null : Double.parseDouble(maxPopStr);

        tablePanel.applyFilters(country, minPop, maxPop);
    }

    private void clearFilters() {
        countryFilterField.setText("");
        minPopField.setText("");
        maxPopField.setText("");
        tablePanel.clearFilters();
    }
}
