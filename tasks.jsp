<%@ page import="java.sql.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Task Manager</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #e3f2fd;
            margin: 0;
            padding: 0;
        }
        .task-container {
            width: 50%;
            margin: auto;
            padding: 20px;
            background: white;
            border-radius: 10px;
            box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.1);
            margin-top: 30px;
        }
        input[type="text"] {
            width: 70%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            padding: 8px 12px;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            font-weight: bold;
        }
        .add-btn {
            background-color: #4CAF50;
            color: white;
        }
        .add-btn:hover {
            background-color: #45a049;
        }
        .delete-btn {
            color: rgb(0, 0, 0);
            text-decoration: none;
        }
        .complete-btn {
            color: rgb(0, 0, 0);
            text-decoration: none;
        }
        
        ul {
            list-style: none;
            padding: 0;
        }
        li {
            padding: 10px;
            border-bottom: 1px solid #ddd;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .completed {
            color: green;
            font-weight: bold;
        }
        .pending {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>

    <h2>📝 Task Manager</h2>
    <div class="task-container">
        <form method="post">
            <input type="text" name="task" placeholder="Enter a new task..." required>
            <button type="submit" name="add" class="add-btn">Add Task</button>
        </form>
        <br>
        <ul>
            <%
                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;
                String message = "";

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/task_db", "root", "#mrm12320");
                    stmt = con.createStatement();

                    if (request.getParameter("add") != null) {
                        String taskName = request.getParameter("task").trim();
                        if (!taskName.isEmpty()) {
                            stmt.executeUpdate("INSERT INTO tasks (task_name, status) VALUES ('" + taskName + "', 'Pending')");
                            response.sendRedirect("tasks.jsp");
                        } else {
                            message = "Task cannot be empty!";
                        }
                    }

                    
                    if (request.getParameter("delete") != null) {
                        int taskId = Integer.parseInt(request.getParameter("delete"));
                        stmt.executeUpdate("DELETE FROM tasks WHERE id=" + taskId);

                        rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM tasks");
                        if (rs.next() && rs.getInt("total") == 0) {
                            stmt.executeUpdate("ALTER TABLE tasks AUTO_INCREMENT = 1");
                        }

                        response.sendRedirect("tasks.jsp");
                    }

                    
                    if (request.getParameter("complete") != null) {
                        int taskId = Integer.parseInt(request.getParameter("complete"));
                        stmt.executeUpdate("UPDATE tasks SET status='Completed' WHERE id=" + taskId);
                        response.sendRedirect("tasks.jsp");
                    }

                    rs = stmt.executeQuery("SELECT * FROM tasks");
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String taskName = rs.getString("task_name");
                        String status = rs.getString("status");
                        String statusClass = status.equals("Completed") ? "completed" : "pending";

                        out.println("<li>" + taskName + " <span class='" + statusClass + "'>" + status + "</span> "
                            + "<a href='?complete=" + id + "' class='complete-btn'>✔ Complete</a> "
                            + "<a href='?delete=" + id + "' class='delete-btn'>❌ Delete</a></li>");
                    }
                } catch (Exception e) {
                    out.println("<p style='color:red;'>Database Error: " + e.getMessage() + "</p>");
                } finally {
                    try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
                    try { if (stmt != null) stmt.close(); } catch (SQLException ignored) {}
                    try { if (con != null) con.close(); } catch (SQLException ignored) {}
                }
            %>
        </ul>
        <p style="color:red;"><%= message %></p>
    </div>

</body>
</html>
