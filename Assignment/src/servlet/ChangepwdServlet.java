package servlet;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

/**
 * Servlet implementation class ChangepwdServlet
 */
@WebServlet("/changepwd")
public class ChangepwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangepwdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8"); 
		String userName = (String)request.getSession().getAttribute("userName");
		String opwd = request.getParameter("mpass");
		String npwd = request.getParameter("newpass");
		System.out.println("change pswd: "+ userName + ", " + opwd);
		int outcome = UserDao.login(userName, opwd);
		if(outcome == -1) {
			request.getSession().setAttribute("message1", "ÃÜÂë´íÎó£¡");
			response.sendRedirect(request.getContextPath()+"/pass.jsp");
		}else {
			if(UserDao.updateUser(userName, npwd)) {
				request.getSession().setAttribute("message1", "ÐÞ¸Ä³É¹¦£¡");
				response.sendRedirect(request.getContextPath()+"/pass.jsp");
			}else {
				request.getSession().setAttribute("message1", "ÐÞ¸ÄÊ§°Ü£¡");
				response.sendRedirect(request.getContextPath()+"/pass.jsp");
			}	
		}
	}

}
