import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task implements Runnable {
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

    @Override
    public void run() {
        try {
            System.out.println("Starting task: " + name);
            System.out.println("Task ID: " + taskId + ", Description: " + description + ", Due Date: " + dueDate + ", Priority: " + priority);
            Thread.sleep(duration * 1000);
            System.out.println("Task '" + name + "' completed!");
        } catch (InterruptedException e) {
            System.out.println("Task '" + name + "' interrupted!");
        }
    }
}

class TaskManager {
    private List<Task> tasks = new ArrayList<>();

    public void addTask(int taskId, String name, String description, String dueDate, String priority, int duration) {
        tasks.add(new Task(taskId, name, description, dueDate, priority, duration));
        System.out.println("Task '" + name + "' added with duration " + duration + " seconds.");
    }

    public void startExecution() {
        List<Thread> threads = new ArrayList<>();

        for (Task task : tasks) {
            Thread thread = new Thread(task);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Execution interrupted!");
            }
        }

        System.out.println("All tasks completed!");
    }
}

public class lab_08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of tasks: ");
        int numTasks = scanner.nextInt();
        scanner.nextLine(); 
        TaskManager taskManager = new TaskManager();

        for (int i = 0; i < numTasks; i++) {
            System.out.println("\nEnter details for Task " + (i + 1));
            System.out.print("Enter Task ID: ");
            int taskId = scanner.nextInt();
            scanner.nextLine(); 
            System.out.print("Enter Title: ");
            String name = scanner.nextLine();
            System.out.print("Enter Description: ");
            String description = scanner.nextLine();
            System.out.print("Enter Due Date (YYYY-MM-DD): ");
            String dueDate = scanner.nextLine();
            System.out.print("Enter Priority (High/Medium/Low): ");
            String priority = scanner.nextLine();
            System.out.print("Enter Duration (seconds): ");
            int duration = scanner.nextInt();
            scanner.nextLine(); 
            
            taskManager.addTask(taskId, name, description, dueDate, priority, duration);
        }

        taskManager.startExecution();
        scanner.close();
    }
}