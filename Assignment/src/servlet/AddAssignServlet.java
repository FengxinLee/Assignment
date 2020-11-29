package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;
import pojo.*;

/**
 * Servlet implementation class AddAssignServlet
 */
@WebServlet("/addassign")
public class AddAssignServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAssignServlet() {
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
		request.setCharacterEncoding("utf-8"); 
		String note = request.getParameter("note");
		String answer = request.getParameter("answer");
		int cid = Integer.parseInt(request.getParameter("cid"));
		System.out.println("addassign: " + note + ", " + answer + ", " + cid);
		
		Assign a = new Assign();
		a.setCourseId(cid);
		a.setAssignAnswer(answer);
		a.setAssignContent(note);
		
		int assignid = AssignDao.addAssign(a, cid);
		
		if(assignid == -1) {
			request.getSession().setAttribute("messageaddass", "作业无法增加！");
			response.sendRedirect(request.getContextPath()+"/assignadd.jsp?cid="+cid);
			return;
		}
		ArrayList<String> names = UserDao.getAllUser(cid);
		boolean addanswer = AnswerDao.addAnswer(names, assignid);
		if(addanswer) {
			request.getSession().setAttribute("messageaddass", "作业及用户记录已增加！");
			response.sendRedirect(request.getContextPath()+"/assignlist.jsp?cid="+cid);
			return;
		}else {
			request.getSession().setAttribute("messageaddass", "用户记录无法增加！");
			response.sendRedirect(request.getContextPath()+"/assignadd.jsp?cid="+cid);
			return;	
		}
	}

}
