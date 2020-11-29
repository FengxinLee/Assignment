package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AnswerDao;

/**
 * Servlet implementation class AddAnswerServlet
 */
@WebServlet("/addanswer")
public class AddAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAnswerServlet() {
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
		String answer = request.getParameter("note");
		int anid = Integer.parseInt(request.getParameter("anid"));
		int aid = Integer.parseInt(request.getParameter("aid"));
		int mark = Integer.parseInt(request.getParameter("mark"));
		
		if(mark != -1) 
		{
			request.getSession().setAttribute("messageuser", "作业已批阅，无法提交！");
			response.sendRedirect(request.getContextPath()+"/useranswer.jsp?aid=" + aid);
			return;
		}
		boolean addanswer = AnswerDao.updateAnswer(anid, answer);
		if(addanswer) {
			request.getSession().setAttribute("messageuser", "作业已提交！");
			response.sendRedirect(request.getContextPath()+"/useranswer.jsp?aid=" + aid);
			return;
		}else {
			request.getSession().setAttribute("messageuser", "作业无法提交！");
			response.sendRedirect(request.getContextPath()+"/useranswer.jsp?aid=" + aid);
			return;	
		}
	}

}
