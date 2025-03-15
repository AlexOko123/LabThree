import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class DetailsPanel extends JPanel {
    private JTextArea detailsArea;

    public DetailsPanel() {
        setBorder(BorderFactory.createTitledBorder("📋 Details"));
        setLayout(new BorderLayout());

        detailsArea = new JTextArea(5, 30);
        detailsArea.setEditable(false);
        add(new JScrollPane(detailsArea), BorderLayout.CENTER);
    }

    public void updateDetails(Map<String, String> rowData) {
        detailsArea.setText("");
        rowData.forEach((key, value) -> detailsArea.append(key + ": " + value + "\n"));
    }
}
