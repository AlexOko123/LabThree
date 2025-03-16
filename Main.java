import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String filePath = ".idea/file3.csv"; // Change to actual path if needed
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose an option:");
        System.out.println("1. Run Console Application");
        System.out.println("2️. Run GUI Application");
        System.out.print("Enter choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // read newline

        if (choice == 1) {
            ConsoleApplication.run(filePath);
        } else if (choice == 2) {
            TableApplication.run(filePath);
        } else {
            System.out.println("Invalid choice. Exiting...");
        }
    }
}
