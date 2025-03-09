import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Ok\\IdeaProjects\\LAB2\\LabThree\\.idea\\file1.csv"; // Change to actual path if needed
        Scanner scanner = new Scanner(System.in);

        System.out.println("📌 Choose an option:");
        System.out.println("1️⃣ Run Console Application");
        System.out.println("2️⃣ Run GUI Application");
        System.out.print("Enter choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            ConsoleApplication.run(filePath);
        } else if (choice == 2) {
            TableApplication.run(filePath);
        } else {
            System.out.println("❌ Invalid choice. Exiting...");
        }
    }
}
