package myservlet_pack;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DbUtil;
import dao.TodoDao;

/**
 * Servlet implementation class UpdateTodoServelet
 */
@WebServlet("/update")
public class UpdateTodoServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 int id = Integer.parseInt(request.getParameter("id"));
	        Connection conn = DbUtil.getConnection();
	        TodoDao dao = new TodoDao(conn);
	        try {
	            dao.updateTodo(id);
	            response.sendRedirect("list");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}