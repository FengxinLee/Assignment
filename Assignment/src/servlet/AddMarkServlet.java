package servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;

/**
 * Servlet implementation class addMarkServlet
 */
@WebServlet("/addmark")
public class AddMarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMarkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
		int cid = Integer.parseInt(request.getParameter("cid"));
		int aid = Integer.parseInt(request.getParameter("aid"));
		int anid = Integer.parseInt(request.getParameter("anid"));
		int isw = Integer.parseInt(request.getParameter("isw"));
		System.out.println("addmark: " + cid + ", " + aid + ", " + anid);
		int mark;
		try 
		{
			mark = Integer.parseInt(request.getParameter("title"));
		}catch(Exception e) 
		{
			request.getSession().setAttribute("messageaddans", "分数无效！");
			response.sendRedirect(request.getContextPath()+"/answerlist.jsp?cid=" + cid + "&aid=" + aid);
			return;
		}
		
		if(isw == -1) 
		{
			request.getSession().setAttribute("messageaddans", "未提交不可批阅！");
			response.sendRedirect(request.getContextPath()+"/answerlist.jsp?cid=" + cid + "&aid=" + aid);
			return;
		}
		if(AnswerDao.updateMark(anid, mark)) 
		{
			request.getSession().setAttribute("messageaddans", "批阅成功！");
			response.sendRedirect(request.getContextPath()+"/answerlist.jsp?cid=" + cid + "&aid=" + aid);
			return;
		}else 
		{
			request.getSession().setAttribute("messageaddans", "批阅失败！");
			response.sendRedirect(request.getContextPath()+"/answerlist.jsp?cid=" + cid + "&aid=" + aid);
			return;
		}
		
	}

}
