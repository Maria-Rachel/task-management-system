import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Lab_10 {
    private JList<String> taskList;
    private JTextField taskInput;
    private ArrayList<String> tasks;

    public Lab_10() {
        JFrame frame = new JFrame("Task Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(240, 240, 240));

        tasks = new ArrayList<>();
        taskList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(taskList);

        taskInput = new JTextField();
        JButton addButton = new JButton("Add Task");
        addButton.setBackground(new Color(100, 200, 100));
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(e -> {
            String task = taskInput.getText().trim();
            if (!task.isEmpty()) {
                tasks.add(task);
                updateTaskList();
                taskInput.setText("");
            }
        });

        JButton deleteButton = new JButton("Delete Task");
        deleteButton.setBackground(new Color(200, 100, 100));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                tasks.remove(selectedIndex);
                updateTaskList();
            }
        });

        JButton completeButton = new JButton("Mark Complete");
        completeButton.setBackground(new Color(100, 100, 200));
        completeButton.setForeground(Color.WHITE);
        completeButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                tasks.set(selectedIndex, "✔ " + tasks.get(selectedIndex));
                updateTaskList();
            }
        });

        JButton clearAllButton = new JButton("Clear All");
        clearAllButton.setBackground(new Color(150, 150, 150));
        clearAllButton.setForeground(Color.WHITE);
        clearAllButton.addActionListener(e -> {
            tasks.clear();
            updateTaskList();
        });

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(taskInput, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);
        buttonPanel.add(completeButton);
        buttonPanel.add(clearAllButton);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void updateTaskList() {
        taskList.setListData(tasks.toArray(new String[0]));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Lab_10::new);
    }
}
