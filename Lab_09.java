import java.util.Scanner;
import java.util.ArrayList;

class Task {
    private int taskId;
    private String name;
    private String description;
    private String dueDate;
    private String priority;
    private int duration;

    public Task(int taskId, String name, String description, String dueDate, String priority, int duration) {
        this.taskId = taskId;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.duration = duration;
    }

    public int getTaskId() {
        return taskId;
    }

    public void execute() {
        try {
            System.out.println("Starting task: " + name);
            System.out.println("Task ID: " + taskId + ", Description: " + description + ", Due Date: " + dueDate + ", Priority: " + priority);
            Thread.sleep(duration * 1000);
            System.out.println("Task '" + name + "' completed!");
        } catch (InterruptedException e) {
            System.out.println("Task '" + name + "' interrupted!");
        }
    }

    @Override
    public String toString() {
        return "Task ID: " + taskId + ", Title: " + name + ", Due Date: " + dueDate + ", Priority: " + priority + ", Duration: " + duration + " seconds.";
    }
}

class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(int taskId, String name, String description, String dueDate, String priority, int duration) {
        tasks.add(new Task(taskId, name, description, dueDate, priority, duration));
        System.out.println("Task '" + name + "' added with duration " + duration + " seconds.");
    }

    public void startExecution() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to execute.");
            return;
        }
        for (Task task : tasks) task.execute();
        System.out.println("All tasks completed!");
    }

    public void showTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        System.out.println("All tasks:");
        for (Task task : tasks) System.out.println(task);
    }

    public void removeTask(int taskId) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskId() == taskId) {
                tasks.remove(i);
                System.out.println("Task with ID " + taskId + " removed.");
                return;
            }
        }
        System.out.println("Task with ID " + taskId + " not found.");
    }

    public void removeAllTasks() {
        tasks.clear();
        System.out.println("All tasks removed.");
    }

    public void searchTask(int taskId) {
        for (Task task : tasks) {
            if (task.getTaskId() == taskId) {
                System.out.println("Task found: " + task);
                return;
            }
        }
        System.out.println("Task with ID " + taskId + " not found.");
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}

public class Lab_09 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add task");
            System.out.println("2. Display tasks");
            System.out.println("3. Remove task");
            System.out.println("4. Start task execution");
            System.out.println("5. Remove all tasks");
            System.out.println("6. Search task");
            System.out.println("7. Total task count");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    System.out.println("Enter Task Details:");
                    System.out.print("Task ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input! Please enter a valid Task ID.");
                        scanner.next();
                    }
                    int taskId = scanner.nextInt();
                    scanner.nextLine();  
                    
                    System.out.print("Task Name: ");
                    String name = scanner.nextLine();
                    
                    System.out.print("Description: ");
                    String description = scanner.nextLine();
                    
                    System.out.print("Due Date (YYYY-MM-DD): ");
                    String dueDate = scanner.nextLine();
                    
                    System.out.print("Priority (High/Medium/Low): ");
                    String priority = scanner.nextLine();
                    
                    System.out.print("Duration (seconds): ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input! Please enter a valid duration.");
                        scanner.next();
                    }
                    int duration = scanner.nextInt();
                    
                    taskManager.addTask(taskId, name, description, dueDate, priority, duration);
                    break;
                    
                case 2:
                    taskManager.showTasks();
                    break;

                case 3:
                    System.out.print("Enter Task ID to remove: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input! Please enter a valid Task ID.");
                        scanner.next();
                    }
                    int removeId = scanner.nextInt();
                    taskManager.removeTask(removeId);
                    break;

                case 4:
                    taskManager.startExecution();
                    break;

                case 5:
                    taskManager.removeAllTasks();
                    break;

                case 6:
                    System.out.print("Enter Task ID to search: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input! Please enter a valid Task ID.");
                        scanner.next();
                    }
                    int searchId = scanner.nextInt();
                    taskManager.searchTask(searchId);
                    break;

                case 7:
                    System.out.println("Total tasks: " + taskManager.size());
                    break;

                case 8:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
