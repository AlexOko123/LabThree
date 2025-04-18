import java.util.Map;

public class CountryFilterStrategy implements FilterStrategy {
    private String country;

    public CountryFilterStrategy(String country) {
        this.country = country;
    }

    @Override
    public boolean apply(Map<String, String> row) {
        if (country == null || country.isEmpty()) return true;
        String countryName = row.get("Country Name");
        return countryName != null && countryName.toLowerCase().contains(country.toLowerCase());
    }
}
