package dao;

import pojo.*;
import java.sql.*;
import java.util.*;

public class CourseDao {
	public static final String DBDRIVER = "com.mysql.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://localhost:3306/assign";
    public static final String DBUSER = "root";
    public static final String DBPASS = "";
    public static Connection con = null;
    
    public static int addCourse(Course c) {
    	conToDB();
    	int i = 0;
    	try
        {
    		PreparedStatement ps = con.prepareStatement("insert into course (course_name, course_des) values(?, ?);");
    		//ps.setInt(1, c.getCourseId());
    		ps.setString(1, c.getCourseName());
    		ps.setString(2, c.getCourseDes());
            ps.execute();
            System.out.println("添加成功！");
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("select max(course_id) from course");
            while(rs.next())
            {
            	i = rs.getInt(1);
            }
        	return i;
        }catch(Exception e){
        	System.out.println("数据库异常！");
        	e.printStackTrace();
        }
    	System.out.println("数据库异常！");
    	return -1;
    }
    
    public static ArrayList<Course> getAllCourse() 
    {
    	conToDB();
    	ArrayList<Course> cs = new ArrayList<Course>();
    	try
        {
    		PreparedStatement ps = con.prepareStatement("select * from course;");
            ResultSet rs = null;
        	
            rs = ps.executeQuery();
            while(rs.next())
            {
            	Course c = new Course();
            	c.setCourseId(rs.getInt(1));
            	c.setCourseName(rs.getString(2));
            	c.setCourseDes(rs.getString(3));
                cs.add(c);
            }

        }catch(Exception e){
        	System.out.println("数据库异常！");
        	e.printStackTrace();
        }
    	return cs;
    }
    
    public static Course getCourseName(int courseid) 
    {
    	conToDB();
    	Course c = new Course();
    	try
        {
    		PreparedStatement ps = con.prepareStatement("select * from course where course_id = ?;");
    		ps.setInt(1, courseid);
            ResultSet rs = null;
            rs = ps.executeQuery();
            while(rs.next())
            {
            	c.setCourseId(rs.getInt(1));
            	c.setCourseName(rs.getString(2));
            	c.setCourseDes(rs.getString(3));
            }

        }catch(Exception e){
        	System.out.println("数据库异常！");
        	e.printStackTrace();
        }
    	return c;
    }
    
    
    public static void conToDB() {
        try
        {
        	if(con == null) {
        		 Class.forName(DBDRIVER);
                 con = DriverManager.getConnection(DBURL,DBUSER,DBPASS);
        	}
        }catch(Exception e){
        	e.printStackTrace();
        }
    }
    
    public static void closeCon() {
    	try
        {
    		if(con != null)
        		con.close();
        }catch(Exception e){
        	e.printStackTrace();
        }
    }
    /*public static void main(String[] args)
    {
    	Course c = new Course();
    	c.setCourseName("C#程序设计");
    	c.setCourseDes("2017年秋，2017级1-10班");
    	System.out.println(CourseDao.addCourse(c));
    	
    	ArrayList<Course> cs = CourseDao.getAllCourse();
    	for(Course c:cs) 
    	{
    		System.out.println(c);
    	}
    	
    	System.out.println(CourseDao.getCourseName(4));
    }*/
}
