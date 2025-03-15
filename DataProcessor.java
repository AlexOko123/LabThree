import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataProcessor {
    private List<Map<String, String>> data;
    private List<Map<String, String>> filteredData;

    public DataProcessor(List<Map<String, String>> data) {
        this.data = data;
        this.filteredData = data; // Initially, filteredData is the same as data
    }

    public List<Map<String, String>> getFilteredData() {
        return filteredData;
    }

    public void applyFilters(String country, Integer minPop, Integer maxPop) {
        filteredData = data.stream().filter(row -> {
            boolean matches = true;

            if (country != null && !country.isEmpty()) {
                matches &= row.get("Country Name").toLowerCase().contains(country.toLowerCase());
            }

            if (minPop != null) {
                matches &= Integer.parseInt(row.get("2015 [YR2015]")) >= minPop;
            }

            if (maxPop != null) {
                matches &= Integer.parseInt(row.get("2015 [YR2015]")) <= maxPop;
            }

            return matches;
        }).collect(Collectors.toList());
    }

    public void clearFilters() {
        this.filteredData = this.data; // Reset to original data
    }

    // ✅ Get First Record
    public Map<String, String> getFirstRecord() {
        return data.isEmpty() ? null : data.get(0);
    }

    // ✅ Get Tenth Record
    public Map<String, String> getTenthRecord() {
        return data.size() >= 10 ? data.get(9) : null;
    }

    // ✅ Get Total Number of Entries
    public int getTotalEntries() {
        return data.size();
    }

    // ✅ Calculate the Minimum Population
    public int getMinPopulation() {
        return filteredData.stream()
                .mapToInt(row -> Integer.parseInt(row.get("2015 [YR2015]")))
                .min()
                .orElse(0);
    }

    // ✅ Calculate the Maximum Population
    public int getMaxPopulation() {
        return filteredData.stream()
                .mapToInt(row -> Integer.parseInt(row.get("2015 [YR2015]")))
                .max()
                .orElse(0);
    }

    // ✅ Calculate the Average Population
    public double getAvgPopulation() {
        return filteredData.stream()
                .mapToInt(row -> Integer.parseInt(row.get("2015 [YR2015]")))
                .average()
                .orElse(0.0);
    }

    // ✅ Generate data for the Chart (Aggregated population per year)
    public Map<String, Long> getChartData() {
        Map<String, Long> chartData = new LinkedHashMap<>();

        // Get all year columns dynamically (excluding 'Country Name' and 'Country Code')
        List<String> yearColumns = new ArrayList<>(data.get(0).keySet());
        yearColumns.remove("Country Name");
        yearColumns.remove("Country Code");

        // Aggregate population for each year
        for (String year : yearColumns) {
            long totalPop = filteredData.stream()
                    .mapToDouble(row->Double.parseDouble(row.get(year)))
                    .mapToLong(Math::round)
                    .sum();
            chartData.put(year, totalPop);
        }

        return chartData;
    }
}
