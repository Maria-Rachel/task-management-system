 import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

class Task {
    protected int taskId;
    protected String title;
    protected LocalDate dueDate;
    protected String priority;

    public Task(int taskId, String title, LocalDate dueDate, String priority) {
        this.taskId = taskId;
        this.title = title;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public void displayTask() {
        System.out.println("Task ID: " + taskId);
        System.out.println("Title: " + title);
        System.out.println("Due Date: " + dueDate);
        System.out.println("Priority: " + priority);
    }
}

class ExtendedTask extends Task {
    private String assignedTo;
    private String status;

    public ExtendedTask(int taskId, String title, LocalDate dueDate, String priority, String assignedTo, String status) {
        super(taskId, title, dueDate, priority);
        this.assignedTo = assignedTo;
        this.status = status;
    }

    @Override
    public void displayTask() {
        super.displayTask();
        System.out.println("Assigned To: " + assignedTo);
        System.out.println("Status: " + status);
    }
}

public class TaskManagementSystem2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            System.out.println("\n Task Management System ");
            System.out.println("1. Add Basic Task");
            System.out.println("2. Add Extended Task");
            System.out.println("3. View All Tasks");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.print("Enter Task ID: ");
                int taskId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter Title: ");
                String title = scanner.nextLine();
                System.out.print("Enter Due Date (YYYY-MM-DD): ");
                LocalDate dueDate = LocalDate.parse(scanner.nextLine());
                System.out.print("Enter Priority (High/Medium/Low): ");
                String priority = scanner.nextLine();

                tasks.add(new Task(taskId, title, dueDate, priority));
                System.out.println("Basic Task added successfully!");
            } else if (option == 2) {
                System.out.print("Enter Task ID: ");
                int taskId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter Title: ");
                String title = scanner.nextLine();
                System.out.print("Enter Due Date (YYYY-MM-DD): ");
                LocalDate dueDate = LocalDate.parse(scanner.nextLine());
                System.out.print("Enter Priority (High/Medium/Low): ");
                String priority = scanner.nextLine();
                System.out.print("Enter Status (Pending/Completed): ");
                String status = scanner.nextLine();

                tasks.add(new ExtendedTask(taskId, title, dueDate, priority, assignedTo, status));
                System.out.println("Extended Task added successfully!");
            } else if (option == 3) {
                if (tasks.isEmpty()) {
                    System.out.println("No tasks available.");
                } else {
                    for (Task task : tasks) {
                        System.out.println("\n--- Task Details ---");
                        task.displayTask();
                    }
                }
            } else if (option == 4) {
                System.out.println("Thank you!");
                scanner.close();
                break;
            } else {
                System.out.println("Invalid option. Please choose again.");
            }
        }
    }
}
