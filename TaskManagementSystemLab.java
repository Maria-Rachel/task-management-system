import java.util.Scanner;
import java.util.ArrayList;

class Task {
    int taskid;
    String name;
    String description;
    String priority;
    

    public Task(int taskid, String name, String description, String priority) {
        this.taskid = taskid;
        this.name = name;
        this.description = description;
        this.priority = priority;
    }
public void execution() {
        try {
            System.out.println("Starting task: " + name);
            System.out.println("Task ID: " + taskid + ", Description: " + description +  ", Priority: " + priority);
            Thread.sleep(duration * 1000); 
            System.out.println("Task '" + name + "' finished!");
        } catch (InterruptedException e) {
            System.out.println("Task '" + name + "' !");
        }
    }
}
class TaskManagement {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(int taskid, String name, String description, String priority) {
        tasks.add(new Task(taskid, name, description, priority));
        System.out.println("Task '" + name + " added");
    }

    public void startExecution() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks created to start execute.");
            return;
        }
        for (Task task : tasks) task.execution(); 
        System.out.println("All tasks completed!");
    }
    public void TasksDisplay() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks created.");
            return;
        }
        System.out.println("All tasks:");
        for (Task1 task : tasks) System.out.println(task);
    }
    public int size() {
        return tasks.size();
    }
}

public class TaskManagementSystemLab {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
        	TaskManagement taskManagement = new TaskManagement();
while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add task");
            System.out.println("2. Display tasks");
            System.out.println("3. Start task execution");
            System.out.println("4. Total task count");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice){
                case 1:
                    System.out.println("Enter Task Details:");
                    System.out.print("Task ID:");
                    int taskid = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Task Name:");
                    String name = scanner.nextLine();
                    System.out.print("Description:");
                    String description = scanner.nextLine();
                    System.out.print("Priority (High/Low/Medium):");
                    String priority = scanner.nextLine();
                    taskManagement.addTask(taskid, name, description, priority);
                    break;
                case 2:
                    taskManagement.TasksDisplay();
                    break;
                case 3:
                    taskManagement.startExecution();
                    break;
                case 4:
                    System.out.println("Number of tasks: " + taskManagement.size());
                    break;
                case 5:
                    System.out.println("Thank you!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
}

}

