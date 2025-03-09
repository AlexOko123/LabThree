import java.util.List;
import java.util.Map;

public class DataProcessor {
    private List<Map<String, String>> data;

    public DataProcessor(List<Map<String, String>> data) {
        this.data = data;
    }

    public Map<String, String> getFirstRecord() {
        return data.isEmpty() ? null : data.get(0);
    }

    public Map<String, String> getTenthRecord() {
        return data.size() >= 10 ? data.get(9) : null;
    }

    public int getTotalEntries() {
        return data.size();
    }
}
