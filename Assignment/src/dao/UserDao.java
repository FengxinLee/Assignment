package dao;

//import pojo.*;
import java.sql.*;
import java.util.*;


public class UserDao {
	public static final String DBDRIVER = "com.mysql.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://localhost:3306/assign";
    public static final String DBUSER = "root";
    public static final String DBPASS = "";
    public static Connection con = null;
    
    public static int login(String username, String passwd) 
    {
    	conToDB();
    	try
        {
    		PreparedStatement ps = con.prepareStatement("select user_passwd,user_role from user where user_name=?;");
            ResultSet rs = null;
        	ps.setString(1, username);
            rs = ps.executeQuery();
            while(rs.next())
            {
                if(rs.getString(1).equals(passwd)) {
                	System.out.println(username + "登录成功！");
                	int i = rs.getInt(2);
                	//closeCon();
                	return i;
                }else {
                	System.out.println("密码错误！");
                	//closeCon();
                	return -1;
                }
            }

        }catch(Exception e){
        	System.out.println("数据库异常！");
        	e.printStackTrace();
        }
    	System.out.println("用户不存在！");
    	//closeCon();
    	return -1;
    }
    
    public static ArrayList<String> getAllUser(int cid) 
    {
    	conToDB();
    	ArrayList<String> users = new ArrayList<String>();
    	try
        {
    		PreparedStatement ps = con.prepareStatement("select user_name from user where course_id=?;");
            ResultSet rs = null;
        	ps.setInt(1, cid);
            rs = ps.executeQuery();
            while(rs.next())
            {
                users.add(rs.getString(1));
            }

        }catch(Exception e){
        	System.out.println("数据库异常！");
        	e.printStackTrace();
        }
    	return users;
    }
    
    public static int getCid(String username) 
    {
    	conToDB();
    	try 
    	{
    		PreparedStatement ps = con.prepareStatement("select course_id from user where user_name = ?");
        	ps.setString(1, username);
        	ResultSet rs = ps.executeQuery();
        	while(rs.next()) 
        	{
        		System.out.println("课程查询成功！");
        		return rs.getInt(1);
        	}
    	}catch(Exception e)
    	{
    		System.out.println("数据库异常！");
        	e.printStackTrace();
        }
    	System.out.println("数据库异常！");
    	//closeCon();
    	return -1;
    }
    
    public static boolean updateUser(String username, String passwd) 
    {
    	conToDB();
    	try
        {
    		PreparedStatement ps = con.prepareStatement("update user set user_passwd=? where user_name=?;");
        	ps.setString(1, passwd);
        	ps.setString(2, username);
            int i = ps.executeUpdate();
            if(i != 1) {
            	System.out.println("数据库异常！");
            	//closeCon();
            	return false;
            }else {
            	System.out.println("更新成功！");
            	//closeCon();
            	return true;
            }

        }catch(Exception e){
        	System.out.println("数据库异常！");
        	e.printStackTrace();
        }
    	System.out.println("数据库异常！");
    	//closeCon();
    	return false;
    }
    
    public static boolean addUser(ArrayList<String> users, int courseid) 
    {
    	conToDB();
    	try
        {
    		PreparedStatement ps = con.prepareStatement("insert into user values(?,'1',1,?);");
    		for(String user : users) 
    		{
    			ps.setString(1, user);
    			ps.setInt(2, courseid);
    			ps.addBatch();
    		}
            ps.executeBatch();
            System.out.println("添加成功！");
        	//closeCon();
        	return true;

        }catch(Exception e){
        	System.out.println("数据库异常！");
        	e.printStackTrace();
        }
    	System.out.println("数据库异常！");
    	//closeCon();
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
    	
    	System.out.println(UserDao.getAllUser(13));
    }
}
