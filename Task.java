package taskPackage;

import java.util.Scanner;
public class Task {
    public static void main(String[] args) {
        TaskService taskService = new TaskService();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Task Management System");
        while (true) {
            System.out.println("\n1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task Completed");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    String name = scanner.nextLine();
                    taskService.addTask(name);
                    break;
                case 2:
                    System.out.println("Task List:");
                    taskService.viewTasks();
                    break;
		case 3:
		    System.out.print("Enter task id: ");
                    int id = scanner.nextInt();
		    scanner.nextLine(); 
                    taskService.markTaskAsCompleted(id);
                    break;
                case 4:
                    System.out.println("Exited");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
}
}
}
}
