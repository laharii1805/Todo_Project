
<%@ page import="java.util.List, model.Todo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>To-Do Task List</title>
    <link rel="stylesheet" href="bootstrap.min.css">

    <script>
        function validateForm() {
            var desc = document.getElementById("description").value;
            if (desc.length < 3) {
                alert("Description must be at least 3 characters long.");
                return false; // Prevent form submission
            }
            return true; // Allow form submission
        }

        function confirmDelete(id) {
            if (confirm("Are you sure you want to delete this task?")) {
                window.location.href = "delete?id=" + id;
            }
        }
        function confirmUpdate(id){
        	if (confirm("Are you sure you wnat to update the status of the task?")){
        		window.location.href = "update?id="+id;
        	}
        }
    </script>

</head>
<body>
    <div class="container">
        <h2 class="mt-4">To-Do Task List</h2>
        <form action="add" method="post" onsubmit="return validateForm()">
            <input type="text" name="title" placeholder="Title" required>
            <input type="text" id="description" name="description" placeholder="Description">
            <input type="date" id="last_date" name = "last_date" placeholder="last_date"/>
            <button type="submit" class="btn btn-primary">Add</button>
        </form>
        <table class="table mt-3">
            <tr><th>Title</th><th>Description</th><th>Last_date</th><th>Status</th><th colspan = 2>Action</th></tr>
            <%
                List<Todo> todos = (List<Todo>) request.getAttribute("todos");
                if (todos != null) {
                    for (Todo todo : todos) {
            %>
            <tr>
                <td><%= todo.getTitle() %></td>
                <td><%= todo.getDescription() %></td>
                <td><%= todo.getLast_date() %></td>
                <td><%= todo.getStatus() %></td>
                <td><a href="javascript:void(0);" onclick="confirmDelete(<%= todo.getId() %>)" class="btn btn-danger">Delete</a></td>
           		<td><a href="javascript:void(0);" onclick="confirmUpdate(<%= todo.getId() %>)" class="btn btn-danger">Update</a></td>
            
            </tr>
            <% } 
                    } %>
        </table>
    </div>
