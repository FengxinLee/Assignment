package servlet;

 
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		System.err.println("login servlet:"+userName+";"+password);
		int outcome = UserDao.login(userName, password);
		if(outcome == 1) {
			response.sendRedirect(request.getContextPath()+"/userindex.jsp");
			request.getSession().setAttribute("userName", userName);
			request.getSession().setAttribute("userRole", 1);
		}else if(outcome == 0) {
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			request.getSession().setAttribute("userName", userName);
			request.getSession().setAttribute("userRole", 0);
		}
		else {
			
			request.getSession().setAttribute("message", "’À√‹¥ÌŒÛ£¨«Î÷ÿ–¬µ«¬º£°");
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}
		
	}
 
}