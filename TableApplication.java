import javax.swing.*;

public class TableApplication {
    public static void run(String filePath) {
        System.out.println("📂 Loading file: " + filePath); //Debugging Line
        SwingUtilities.invokeLater(() -> new TableFrame(filePath));
    }
}
