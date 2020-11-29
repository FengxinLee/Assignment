<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*"%>
<%@ page import="dao.*"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String user = (String)session.getAttribute("userName");
int role;
try{
 	role = Integer.parseInt(session.getAttribute("userRole").toString());
}catch(Exception e)
{
	System.out.println(session.getAttribute("userRole"));
	 role = -1;
}
int cid = UserDao.getCid(user);
String cname = CourseDao.getCourseName(cid).getCourseName();
%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>作业提交系统</title>  
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>   
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1><img src="images/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />作业提交系统-学生端   </h1>
    
  </div>
  <div class="head-l">&nbsp;&nbsp;
  	<a class="button button-little bg-red" href="/Assignment/login.jsp"><span class="icon-power-off"></span> 退出登录</a> </div>
</div>
<div class="leftnav">
  <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
  <h2><span class="icon-user"></span>基本功能</h2>
  <ul style="display:block">
    <li><a href="userassignlist.jsp?cid=<%=cid %>" target="right"><span class="icon-caret-right"></span>作业管理</a></li>
    <li><a href="pass.jsp" target="right"><span class="icon-caret-right"></span>修改密码</a></li>
    <li><a href="welcome.html" target="right"><span class="icon-caret-right"></span>内容建设中</a></li>  
  </ul>   
</div>
<%
if(user == null || user.equals("null") || role != 1)
{
	response.sendRedirect(request.getContextPath()+"/login.jsp");
%>
	<script type="text/javascript">
    alert("登录已过期！");
	</script>
<%
}
%>
<script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
	  $(this).toggleClass("on"); 
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
</script>
<ul class="bread">
  <li>欢迎学生：<%=user %>，学习课程：<%=cname %></li>
</ul>
<div class="admin">
  <iframe scrolling="auto" rameborder="0" src="userassignlist.jsp?cid=<%=cid %>" name="right" width="100%" height="100%"></iframe>
</div>
<div style="text-align:center;">
</div>
</body>
</html>