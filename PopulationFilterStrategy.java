import java.util.Map;

public class PopulationFilterStrategy implements FilterStrategy {
    private Double minPop;
    private Double maxPop;

    public PopulationFilterStrategy(Double minPop, Double maxPop) {
        this.minPop = minPop;
        this.maxPop = maxPop;
    }

    @Override
    public boolean apply(Map<String, String> row) {
        try {
            double population = Double.parseDouble(row.get("2015 [YR2015]").replace(",", "").trim());
            if (minPop != null && population < minPop) return false;
            if (maxPop != null && population > maxPop) return false;
            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }
}
