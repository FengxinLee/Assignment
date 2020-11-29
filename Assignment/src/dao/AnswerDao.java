package dao;

import java.sql.*;
import java.util.ArrayList;

import pojo.*;

public class AnswerDao {
	public static final String DBDRIVER = "com.mysql.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://localhost:3306/assign";
    public static final String DBUSER = "root";
    public static final String DBPASS = "";
    public static Connection con = null;
    
    public static boolean addAnswer(ArrayList<String> ns, int aid) {
    	conToDB();
    	try
        {
    		PreparedStatement ps = con.prepareStatement("insert into answer (user_id, assign_id) values(?, ?);");
    		for(String n:ns) 
    		{
    			ps.setString(1, n);
    			ps.setInt(2, aid);
    			ps.addBatch();
    		}
            ps.executeBatch();
            System.out.println("添加成功！");
        	return true;
        }catch(Exception e){
        	System.out.println("数据库异常！");
        	e.printStackTrace();
        }
    	System.out.println("数据库异常！");
    	return false;
    }
    
    public static ArrayList<Answer> getAllAnswer(int aid) 
    {
    	conToDB();
    	ArrayList<Answer> ans = new ArrayList<Answer>();
    	try
        {
    		PreparedStatement ps = con.prepareStatement("select * from answer where assign_id=?;");
            ResultSet rs = null;
        	ps.setInt(1, aid);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Answer a = new Answer();
                a.setAnswerId(rs.getInt(1));
                a.setUserId(rs.getString(2));
                a.setAssignId(rs.getInt(3));
                a.setAnswerContent(rs.getString(4));
                a.setAnswerMark(rs.getInt(6));
                a.setAnswerIswrite(rs.getInt(5));
                ans.add(a);
            }

        }catch(Exception e){
        	System.out.println("数据库异常！");
        	e.printStackTrace();
        }
    	return ans;
    }
    
    public static Answer getAnswer(int anid) 
    {
    	conToDB();
    	Answer a = new Answer();
    	try
        {
    		PreparedStatement ps = con.prepareStatement("select * from answer where answer_id = ?;");
            ps.setInt(1, anid);
    		ResultSet rs = null;
        	
            rs = ps.executeQuery();
            while(rs.next())
            {
            	a.setAnswerId(rs.getInt(1));
                a.setUserId(rs.getString(2));
                a.setAssignId(rs.getInt(3));
                a.setAnswerContent(rs.getString(4));
                a.setAnswerMark(rs.getInt(6));
                a.setAnswerIswrite(rs.getInt(5));
            }

        }catch(Exception e){
        	System.out.println("数据库异常！");
        	e.printStackTrace();
        }
    	return a;
    }
    
    public static Answer findAnswer(int aid, String user) 
    {
    	conToDB();
    	Answer a = new Answer();
    	try
        {
    		PreparedStatement ps = con.prepareStatement("select * from answer where assign_id = ? and user_id = ?;");
            ps.setInt(1, aid);
            ps.setString(2, user);
    		ResultSet rs = null;
        	
            rs = ps.executeQuery();
            while(rs.next())
            {
            	a.setAnswerId(rs.getInt(1));
                a.setUserId(rs.getString(2));
                a.setAssignId(rs.getInt(3));
                a.setAnswerContent(rs.getString(4));
                a.setAnswerMark(rs.getInt(6));
                a.setAnswerIswrite(rs.getInt(5));
            }

        }catch(Exception e){
        	System.out.println("数据库异常！");
        	e.printStackTrace();
        }
    	return a;
    }
    public static boolean updateMark(int anid, int mark) {
    	conToDB();
    	try
        {
    		PreparedStatement ps = con.prepareStatement("update answer set answer_mark = ? where answer_id = ?;");
    		ps.setInt(1, mark);
    		ps.setInt(2, anid);
            ps.executeUpdate();
            System.out.println("添加成功！");
        	return true;
        }catch(Exception e){
        	System.out.println("数据库异常！");
        	e.printStackTrace();
        }
    	System.out.println("数据库异常！");
    	return false;
    }
    
    public static boolean updateAnswer(int anid, String content) {
    	conToDB();
    	try
        {
    		PreparedStatement ps = con.prepareStatement("update answer set answer_content = ?, answer_iswrite = 1 where answer_id = ?;");
    		ps.setString(1, content);
    		ps.setInt(2, anid);
            ps.executeUpdate();
            System.out.println("添加成功！");
        	return true;
        }catch(Exception e){
        	System.out.println("数据库异常！");
        	e.printStackTrace();
        }
    	System.out.println("数据库异常！");
    	return false;
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
