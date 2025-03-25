import java.time.LocalDate;
import java.util.Scanner;

class Task {
    int taskId;
    String title;
    LocalDate dueDate;

    public Task(int taskId, String title, LocalDate dueDate) {
        this.taskId = taskId;
        this.title = title;
        this.dueDate = dueDate;
    }

    public void displayTask() {
        System.out.println("Task ID: " + taskId + ", Title: " + title + ", Due Date: " + dueDate);
    }

    protected void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}

public class Lab02 {
    private static Task task = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Task");
            System.out.println("2. View Task");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            System.out.println();

            if (option == 1) {
                System.out.print("Enter Task ID: ");
                int taskId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter Title: ");
                String title = scanner.nextLine();
                System.out.print("Enter Due Date (YYYY-MM-DD): ");
                String dueDateStr = scanner.nextLine();
                LocalDate dueDate = LocalDate.parse(dueDateStr);

                task = new Task(taskId, title, dueDate);
                System.out.println("Task added!");
            } else if (option == 2) {
                if (task == null) {
                    System.out.println("No task available to view.");
                } else {
                    task.displayTask();
                }
            } else if (option == 3) {
                System.out.println("Thank you!");
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
