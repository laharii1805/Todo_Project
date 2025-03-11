package dao;

import java.sql.*;
import java.util.*;
import model.Todo;

public class TodoDao {
    private Connection conn;

    public TodoDao(Connection conn) {
        this.conn = conn;
    }

    public List<Todo> getAllTodos() throws SQLException {
        List<Todo> todos = new ArrayList<>();
        String sql = "SELECT * FROM todos";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                todos.add(new Todo(
                    rs.getInt("id"), 
                    rs.getString("title"), 
                    rs.getString("description"), 
                    rs.getDate("last_date"),  
                    rs.getString("status")
                ));
            }
        }
        return todos;
    }

    public void addTodo(Todo todo) throws SQLException {
        String sql = "INSERT INTO todos (title, description, last_date, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, todo.getTitle());
            ps.setString(2, todo.getDescription());
            ps.setDate(3, todo.getLast_date());
            ps.setString(4, todo.getStatus());
            ps.executeUpdate();
        }
    }

    public void deleteTodo(int id) throws SQLException {
        String sql = "DELETE FROM todos WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
    
    public void updateTodo(int id) throws SQLException {
        String sql = "Update todos set status='Completed' WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}

/*package dao;
import java.sql.*;
import java.util.*;
import model.Todo;

public class TodoDao {
    private Connection conn;

    public TodoDao(Connection conn) {
        this.conn = conn;
    }

    public List<Todo> getAllTodos() throws SQLException {
        List<Todo> todos = new ArrayList<>();
        String sql = "SELECT * FROM todos";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                todos.add(new Todo(rs.getInt("id"), rs.getString("title"), rs.getString("description"),java.sql.Date.valueOf(rs.getLast_date("last_date")),rs.getString("status")));
            }
        }
        return todos;
    }

    public void addTodo(Todo todo) throws SQLException {
        String sql = "INSERT INTO todos (title, description, last_date, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, todo.getTitle());
            ps.setString(2, todo.getDescription());
            ps.setDate(3, todo.getLast_date());
            ps.setString(4, todo.getStatus());
            ps.executeUpdate();
        }
    }

    public void deleteTodo(int id) throws SQLException {
        String sql = "DELETE FROM todos WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
*/