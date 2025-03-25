import java.util.Scanner;

class Task {
    int taskId;
    String title;
    String description;
    String dueDate;
    String priority;


    Task() {
        this.taskId = 01;
        this.title = "title";
        this.dueDate = "2025-01-03";
        this.description = "No Description"; 
        this.priority = "Medium"; 
    }

    Task(int taskId, String title, String description, String dueDate, String priority) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    
    void display() {
        System.out.println("Task ID: " + taskId + ", Title: " + title + ", Due Date: " + dueDate + ", Priority: " + priority);
    }

    void display(boolean showDetails) {
        if (showDetails) {
            System.out.println("Task ID: " + taskId);
            System.out.println("Title: " + title);
            System.out.println("Description: " + description);
            System.out.println("Due Date: " + dueDate);
            System.out.println("Priority: " + priority);
        } else {
            display();
        }
    }
}

public class TaskManagementSystem1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of tasks: ");
        int numTasks = scanner.nextInt();
        scanner.nextLine(); 

        Task[] tasks = new Task[numTasks];

        for (int i = 0; i < numTasks; i++) {
            System.out.println("\nEnter details for Task " + (i + 1));
            System.out.print("Enter Task ID: ");
            int taskId = scanner.nextInt();
            scanner.nextLine(); // Consume leftover newline

            System.out.print("Enter Title: ");
            String title = scanner.nextLine();
            System.out.print("Enter Due Date (YYYY-MM-DD): ");
            String dueDate = scanner.nextLine();
            System.out.print("Enter Priority (High/Medium/Low, or press Enter for default): ");
            String priority = scanner.nextLine();
            if (priority.isEmpty()) {
                priority = "Medium";
            }
            System.out.print("Enter Description (or press Enter for default): ");
            String description = scanner.nextLine();

            if (description.isEmpty()) {
                tasks[i] = new Task(); 
            } else {
                tasks[i] = new Task(taskId, title, description, dueDate, priority);
            }
        }

        System.out.println("\nDisplaying all tasks:");
        for (Task task : tasks) {
            task.display(true); 
            System.out.println("_______________________");
        }

        scanner.close();
    }
}
