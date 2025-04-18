import java.util.Map;

public class CombinedFilterStrategy implements FilterStrategy {
    private FilterStrategy first;
    private FilterStrategy second;

    public CombinedFilterStrategy(FilterStrategy first, FilterStrategy second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean apply(Map<String, String> row) {
        return first.apply(row) && second.apply(row);
    }
}
