import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

public class TablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private DataProcessor dataProcessor;
    private List<String> headers;  // ✅ Define headers
    private DetailsPanel detailsPanel;

    public TablePanel(String filePath) {
        setLayout(new BorderLayout());

        // Load Data
        CSVReader reader = new CSVReader(filePath);
        dataProcessor = new DataProcessor(reader.getData());
        headers = reader.getHeaders(); // ✅ Retrieve headers

        // Create Table Model
        tableModel = new DefaultTableModel();
        for (String header : headers) {
            tableModel.addColumn(header);
        }
        populateTable(dataProcessor.getFilteredData());

        // Create Table
        table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);

        // Add Selection Listener
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1 && detailsPanel != null) {
                    detailsPanel.updateDetails(dataProcessor.getFilteredData().get(row));
                }
            }
        });

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void populateTable(List<Map<String, String>> data) {
        tableModel.setRowCount(0); // Clear table
        for (Map<String, String> row : data) {
            tableModel.addRow(headers.stream().map(row::get).toArray());
        }
    }

    public void applyFilters(String country, Double minPop, Double maxPop) {
        dataProcessor.applyFilters(country, minPop, maxPop);
        populateTable(dataProcessor.getFilteredData());
    }


    public void clearFilters() {
        applyFilters(null, null, null); // ✅ Reset table without integer constraints
    }


    public DataProcessor getDataProcessor() {
        return dataProcessor;
    }

    public void setDetailsPanel(DetailsPanel detailsPanel) {
        this.detailsPanel = detailsPanel;
    }
}
