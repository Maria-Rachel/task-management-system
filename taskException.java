import java.util.Scanner;

class InvalidTaskException extends Exception {
    public InvalidTaskException(String message) {
        super(message);
}
}
public class taskException {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String taskName = "";
        int priority = 0;
        boolean taskAdded = false;
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Task");
            System.out.println("2. Display Task");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter task name: ");
                        taskName = scanner.nextLine();
                        if (taskName.trim().isEmpty()) {
                            throw new InvalidTaskException("Task name cannot be empty!");
                        }
                        System.out.print("Enter task priority (1-5): ");
                        priority = scanner.nextInt();
                        if (priority < 1 || priority > 5) {
                            throw new InvalidTaskException("Priority must be between 1 and 5!");
                        }
                        taskAdded = true;
                        System.out.println("Task added successfully.");
                        break;
                    case 2:
                        if (!taskAdded) {
                            throw new InvalidTaskException("No task available. Add a task first!");
                        }
                        System.out.println("\nTask Details:");
                        System.out.println("Name: " + taskName);
                        System.out.println("Priority: " + priority);
                        break;
                    case 3:
                        System.out.println("Exiting program.");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice! Please select a valid option.");
                }
            } catch (InvalidTaskException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter correct values.");
                scanner.nextLine(); 
}
}
}
}
