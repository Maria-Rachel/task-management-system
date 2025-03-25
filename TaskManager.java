import java.util.Scanner;

public class TaskManager{
    private static StringBuffer[] tasks = new StringBuffer[100];
    private static int taskCount = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("\n1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Edit Task");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter task: ");
                    tasks[taskCount++] = new StringBuffer(scanner.nextLine());
                    System.out.println("Task added.");
                    break;
                case 2:
                    System.out.println("Tasks:");
                    for (int i = 0; i < taskCount; i++){
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                    break;
                case 3:
                    System.out.print("Enter task number to edit: ");
                    int taskNum = scanner.nextInt();
                    scanner.nextLine();
                    if (taskNum < 1 || taskNum > taskCount){
                        System.out.println("Invalid number.");
                    } else {
                        System.out.print("New task Name: ");
                        tasks[taskNum - 1].replace(0, tasks[taskNum - 1].length(), scanner.nextLine());
                        System.out.println("Task updated.");
                    }
                    break;
                case 4:
                    System.out.println("Exited");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option.");
}
}
}
}