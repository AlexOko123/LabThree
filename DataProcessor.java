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

    public void applyFilters(String country, Double minPop, Double maxPop) {
        filteredData = data.stream().filter(row -> {
            boolean matches = true;

            // Country Name Filter
            if (country != null && !country.isEmpty()) {
                matches &= row.get("Country Name").toLowerCase().contains(country.toLowerCase());
            }

            // Population Filters (Use Double Instead of Integer)
            try {
                double population = Double.parseDouble(row.get("2015 [YR2015]").replace(",", "").trim());

                if (minPop != null) {
                    matches &= population >= minPop; // Compare as double
                }

                if (maxPop != null) {
                    matches &= population <= maxPop; // Compare as double
                }
            } catch (NumberFormatException | NullPointerException e) {
                matches = false; // If parsing fails, exclude this row
            }

            return matches;
        }).collect(Collectors.toList());
    }


    public void clearFilters() {
        this.filteredData = this.data; // Reset to original data
    }

    // Get First Record
    public Map<String, String> getFirstRecord() {
        return data.isEmpty() ? null : data.get(0);
    }

    // Get Tenth Record
    public Map<String, String> getTenthRecord() {
        return data.size() >= 10 ? data.get(9) : null;
    }

    // Get Total Number of Entries
    public int getTotalEntries() {
        return data.size();
    }

    // Calculate the Minimum Population
    public long getMinPopulation() {
        // Default to 0 if no valid values exist
        return (long) filteredData.stream()
                .mapToDouble(row -> {
                    try {
                        return Double.parseDouble(row.get("2015 [YR2015]").replace(",", "").trim());
                    } catch (NumberFormatException | NullPointerException e) {
                        return Double.MAX_VALUE; // Use a very large value so it doesn't affect min()
                    }
                })
                .min()
                .orElse(0.0);// Convert to long

    }


    // Calculate the Maximum Population
    public long getMaxPopulation() {
        return (long) filteredData.stream()
                .mapToDouble(row -> {
                    try {
                        return Double.parseDouble(row.get("2015 [YR2015]").replace(",", "").trim());
                    } catch (NumberFormatException | NullPointerException e) {
                        return Double.MIN_VALUE; // Use a very small value so it doesn't affect max()
                    }
                })
                .max()
                .orElse(0.0);

    }

    //  Calculate the Average Population
    public double getAvgPopulation() {
        return Math.round(filteredData.stream()
                .mapToDouble(row -> {
                    try {
                        return Double.parseDouble(row.get("2015 [YR2015]").replace(",", "").trim());
                    } catch (NumberFormatException | NullPointerException e) {
                        return 0.0; //  Default to 0
                    }
                })
                .average()
                .orElse(0.0));
    }

    // Generate data for the Chart (Aggregated population per year)
    public Map<String, Long> getChartData() {
        Map<String, Long> chartData = new LinkedHashMap<>();

        // Get all year columns dynamically (excluding 'Country Name' and 'Country Code')
        List<String> yearColumns = new ArrayList<>(data.get(0).keySet());
        yearColumns.remove("Country Name");
        yearColumns.remove("Country Code");

        for (String year : yearColumns) {
            long totalPop = filteredData.stream()
                    .mapToDouble(row -> {
                        try {
                            String value = row.get(year).replace(",", "").trim(); //  Remove commas
                            return Double.parseDouble(value); //  Parse as double
                        } catch (NumberFormatException | NullPointerException e) {
                            return 0.0; // Default to 0 if parsing fails
                        }
                    })
                    .mapToLong(Math::round) // Round decimal values safely
                    .sum();
            chartData.put(year, totalPop);
        }

        return chartData;
    }
}
