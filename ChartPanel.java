import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ChartPanel extends JPanel {
    private Map<String, Long> chartData;

    public ChartPanel(DataProcessor dataProcessor) {
        setBorder(BorderFactory.createTitledBorder("📈 Population Chart"));
        this.chartData = dataProcessor.getChartData();  // Get year-wise population data
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (chartData == null || chartData.isEmpty()) {
            g2.drawString("No data available", getWidth() / 2 - 50, getHeight() / 2);
            return;
        }

        int padding = 50;
        int width = getWidth() - 2 * padding;
        int height = getHeight() - 2 * padding;

        // Find max value for scaling
        long maxValue = chartData.values().stream().max(Long::compare).orElse(1L);

        // Determine bar width
        int barWidth = width / chartData.size();
        int x = padding;

        g2.setColor(Color.BLUE);

        for (Map.Entry<String, Long> entry : chartData.entrySet()) {
            int barHeight = (int) ((entry.getValue() / (double) maxValue) * height);
            g2.fillRect(x, getHeight() - barHeight - padding, barWidth - 10, barHeight);
            g2.setColor(Color.BLACK);
            g2.drawString(entry.getKey(), x + (barWidth / 4), getHeight() - 5);
            x += barWidth;
        }
    }
}
