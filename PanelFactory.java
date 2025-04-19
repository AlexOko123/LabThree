import javax.swing.*;

public class PanelFactory {

    public static JPanel createPanel(String panelType, Object... dependencies) {
        switch (panelType) {
            case "Table":
                return new TablePanel((String) dependencies[0]);

            case "Stats":
                return new StatsPanel((DataProcessor) dependencies[0]);

            case "Chart":
                return new PopulationChartPanel((DataProcessor) dependencies[0]);

            case "Details":
                return new DetailsPanel();

            case "Filter":
                return new FilterPanel(
                        (TablePanel) dependencies[0],
                        (StatsPanel) dependencies[1],
                        (PopulationChartPanel) dependencies[2]
                );

            default:
                throw new IllegalArgumentException("Unknown panel type: " + panelType);
        }
    }
}
