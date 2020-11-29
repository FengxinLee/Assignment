package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CourseDao;
import dao.UserDao;
import pojo.Course;

/**
 * Servlet implementation class AddCourseServlet
 */
@WebServlet("/addcourse")
public class AddCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCourseServlet() {
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
		String title = request.getParameter("title");
		String note = request.getParameter("note");
		String content = request.getParameter("content");
		System.out.println("addcourse: " + title + ", " + note + ", \n" + content);
		String[] cons = content.split("\n");
		ArrayList<String> names = new ArrayList<String>();
		for(String con : cons) 
		{
			if(con.length() >= 10 || con.length() <= 3) {
				request.getSession().setAttribute("messageadd", "学号格式错误！学号应为4-8位数字！");
				response.sendRedirect(request.getContextPath()+"/add.jsp");
				return;
			}
			con = con.replace("\n", "");
			names.add(con);
		}
		
		Course c = new Course();
		c.setCourseDes(note);
		c.setCourseName(title);
		int courseid = CourseDao.addCourse(c);
		if(courseid == -1) {
			request.getSession().setAttribute("messageadd", "课程无法增加！");
			response.sendRedirect(request.getContextPath()+"/add.jsp");
			return;
		}
		boolean addusers = UserDao.addUser(names, courseid);
		if(addusers) {
			request.getSession().setAttribute("messageadd", "课程及用户已增加！用户初始密码为1！");
			response.sendRedirect(request.getContextPath()+"/add.jsp");
			return;
		}else {
			request.getSession().setAttribute("messageadd", "用户无法增加！");
			response.sendRedirect(request.getContextPath()+"/add.jsp");
			return;	
		}
	}

}
