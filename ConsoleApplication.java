import java.util.Map;

public class ConsoleApplication {
    public static void run(String filePath) {
        CSVReader reader = new CSVReader(filePath);
        DataProcessor processor = new DataProcessor(reader.getData());

        Map<String, String> firstRecord = processor.getFirstRecord();
        Map<String, String> tenthRecord = processor.getTenthRecord();
        int totalEntries = processor.getTotalEntries();

        System.out.println("First Data Item:");
        System.out.println(firstRecord != null ? firstRecord : "No data available.");

        System.out.println("\n Tenth Data Item:");
        System.out.println(tenthRecord != null ? tenthRecord : "Less than 10 records available.");

        System.out.println("\n Total Number of Entries: " + totalEntries);
    }
}
