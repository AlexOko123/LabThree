import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVReader {
    private List<Map<String, String>> data;
    private List<String> headers;

    public CSVReader(String filePath) {
        data = new ArrayList<>();
        headers = new ArrayList<>();
        loadData(filePath);
    }

    private void loadData(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String[] headerArray = br.readLine().split(",");
            headers.addAll(Arrays.asList(headerArray));

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Map<String, String> row = new LinkedHashMap<>();
                for (int i = 0; i < headers.size() && i < values.length; i++) {
                    row.put(headers.get(i), values[i]);
                }
                data.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Map<String, String>> getData() {
        return data;
    }

    public List<String> getHeaders() {
        return headers;
    }
}
