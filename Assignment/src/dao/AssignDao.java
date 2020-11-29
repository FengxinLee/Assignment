package dao;

import java.util.*;
import pojo.*;
import java.sql.*;
public class AssignDao {
	public static final String DBDRIVER = "com.mysql.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://localhost:3306/assign";
    public static final String DBUSER = "root";
    public static final String DBPASS = "";
    public static Connection con = null;
    
    public static int addAssign(Assign a, int cid) {
    	conToDB();
    	int i = 0;
    	try
        {
    		PreparedStatement ps = con.prepareStatement("insert into assign (course_id, assign_content, assign_answer) values(?, ?, ?);");
    		ps.setInt(1, cid);
    		ps.setString(2, a.getAssignContent());
    		ps.setString(3, a.getAssignAnswer());
            ps.execute();
            System.out.println("添加成功！");
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("select max(assign_id) from assign;");
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
    
    public static ArrayList<Assign> getAllAssign(int cid) 
    {
    	conToDB();
    	ArrayList<Assign> as = new ArrayList<Assign>();
    	try
        {
    		PreparedStatement ps = con.prepareStatement("select * from assign where course_id = ?;");
            ps.setInt(1, cid);
    		ResultSet rs = null;
        	
            rs = ps.executeQuery();
            while(rs.next())
            {
            	Assign a = new Assign();
            	a.setAssignId(rs.getInt(1));
            	a.setCourseId(cid);
            	a.setAssignContent(rs.getString(3));
            	a.setAssignAnswer(rs.getString(4));
                as.add(a);
            }

        }catch(Exception e){
        	System.out.println("数据库异常！");
        	e.printStackTrace();
        }
    	return as;
    }
    
    public static Assign getAssign(int aid) 
    {
    	conToDB();
    	Assign a = new Assign();
    	try
        {
    		PreparedStatement ps = con.prepareStatement("select * from assign where assign_id = ?;");
            ps.setInt(1, aid);
    		ResultSet rs = null;
            rs = ps.executeQuery();
            while(rs.next())
            {
            	a.setAssignId(rs.getInt(1));
            	a.setCourseId(rs.getInt(2));
            	a.setAssignContent(rs.getString(3));
            	a.setAssignAnswer(rs.getString(4));
            }

        }catch(Exception e){
        	System.out.println("数据库异常！");
        	e.printStackTrace();
        }
    	return a;
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
    public static void main(String[] args)
    {
    	
    }
}
