import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class TableFrame extends JFrame {
    public TableFrame(String filePath) {
        setTitle("📊 CSV Data Viewer"); // ✅ Clear title
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window

        try {
            CSVReader reader = new CSVReader(filePath);
            List<Map<String, String>> data = reader.getData();
            List<String> headers = reader.getHeaders();

            // ✅ Handle empty data gracefully
            if (data.isEmpty()) {
                JOptionPane.showMessageDialog(this, "⚠️ Error: No data found in file!", "Data Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // ✅ Create table with labeled columns
            DefaultTableModel tableModel = new DefaultTableModel();
            for (String header : headers) {
                tableModel.addColumn(header);
            }

            // ✅ Populate table with data
            data.forEach(row -> tableModel.addRow(headers.stream().map(row::get).toArray()));

            JTable table = new JTable(tableModel);
            table.setAutoCreateRowSorter(true); // ✅ Enable sorting

            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane, BorderLayout.CENTER); // ✅ Ensure TablePanel

            setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "❌ Error loading data: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
