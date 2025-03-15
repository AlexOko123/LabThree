import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel {
    private JLabel minLabel, maxLabel, avgLabel;

    public StatsPanel(DataProcessor dataProcessor) {
        setLayout(new GridLayout(3, 1));
        setBorder(BorderFactory.createTitledBorder("📊 Statistics"));

        minLabel = new JLabel();
        maxLabel = new JLabel();
        avgLabel = new JLabel();

        updateStats(dataProcessor);

        add(minLabel);
        add(maxLabel);
        add(avgLabel);
    }

    public void updateStats(DataProcessor dataProcessor) {
        minLabel.setText("📉 Min Population: " + dataProcessor.getMinPopulation());
        maxLabel.setText("📈 Max Population: " + dataProcessor.getMaxPopulation());
        avgLabel.setText("📊 Avg Population: " + dataProcessor.getAvgPopulation());
    }
}
