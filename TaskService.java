package taskPackage;

public class TaskService {
    private String[] tasks = new String[100]; 
    private int taskCount = 0;

    public void addTask(String task) {
        if (taskCount >= tasks.length) {
            System.out.println("Task limit reached. Cannot add more tasks.");
            return;
        }
        tasks[taskCount] = task;
        System.out.println("Task added: ID " + (taskCount + 1) + ", Task: " + task);
        taskCount++;
}
    public void viewTasks() {
        if (taskCount == 0) {
            System.out.println("No tasks available.");
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.println("Task ID: " + (i + 1) + ", Task: " + tasks[i]);
}
}
}

    public void markTaskAsCompleted(int id) {
        if (id > 0 && id <= taskCount) {
            System.out.println("Task marked as completed: ID " + id + ", Task: " + tasks[id - 1]);
        } else {
            System.out.println("Task with ID " + id + " not found.");
}
}
}
