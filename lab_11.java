import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class lab_11 {
    private JList<String> taskList;
    private JTextField taskInput;
    private DefaultListModel<String> taskModel;
    private Connection con;
    private Statement stmt;

    public lab_11() {
        connectDatabase();

        JFrame frame = new JFrame("Task Management System");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        taskModel = new DefaultListModel<>();
        taskList = new JList<>(taskModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        taskInput = new JTextField();
        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(e -> addTask());

        JButton deleteButton = new JButton("Delete Task");
        deleteButton.addActionListener(e -> deleteTask());

        JButton completeButton = new JButton("Mark Complete");
        completeButton.addActionListener(e -> markComplete());

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> loadTasks());

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(taskInput, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);
        buttonPanel.add(completeButton);
        buttonPanel.add(refreshButton);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        loadTasks();
    }

    private void connectDatabase() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/task_db", "root", "#mrm12320");
            stmt = con.createStatement();
            String createTable = "CREATE TABLE IF NOT EXISTS tasks (id INT AUTO_INCREMENT PRIMARY KEY, task_name VARCHAR(255), status VARCHAR(50))";
            stmt.executeUpdate(createTable);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    
    private void loadTasks() {
        taskModel.clear();
        try {
            ResultSet rs = stmt.executeQuery("SELECT id, task_name, status FROM tasks");
            while (rs.next()) {
                String task = rs.getInt(1) + ": " + rs.getString(2) + " (" + rs.getString(3) + ")";
                taskModel.addElement(task);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

   
    private void addTask() {
        String taskName = taskInput.getText().trim();
        if (taskName.equals("")) {
            JOptionPane.showMessageDialog(null, "Enter a task!");
            return;
        }
        try {
            stmt.executeUpdate("INSERT INTO tasks (task_name, status) VALUES ('" + taskName + "', 'Pending')");
            JOptionPane.showMessageDialog(null, "Task Added!");
            taskInput.setText("");
            loadTasks();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void deleteTask() {
        int index = taskList.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Select a task to delete.");
            return;
        }

        String selectedTask = taskModel.get(index);
        int taskId = Integer.parseInt(selectedTask.split(":")[0]);

        try {
            stmt.executeUpdate("DELETE FROM tasks WHERE id=" + taskId);
            JOptionPane.showMessageDialog(null, "Task Deleted!");
            loadTasks();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    
    private void markComplete() {
        int index = taskList.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Select a task to mark as complete.");
            return;
        }

        String selectedTask = taskModel.get(index);
        int taskId = Integer.parseInt(selectedTask.split(":")[0]);

        try {
            stmt.executeUpdate("UPDATE tasks SET status='Completed' WHERE id=" + taskId);
            JOptionPane.showMessageDialog(null, "Task Marked as Completed!");
            loadTasks();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new lab_11();
    }
}
