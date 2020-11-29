<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="pojo.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

ArrayList<Course> cs = CourseDao.getAllCourse();
%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title></title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
</head>
<body>
<form method="post" action="" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 课程列表</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <li> <a class="button border-main icon-plus-square-o" href="add.jsp"> 添加课程</a> </li>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th>课程ID</th>
        <th>课程名称</th>
        <th>课程描述</th>
        <th width="310">操作</th>
      </tr>
      <%
      for(Course c: cs)
      {
      	%> 
      	<tr>
      		<td> <%= c.getCourseId() %> </td>
      		<td> <%= c.getCourseName() %> </td>
      		<td> <%= c.getCourseDes() %> </td>
      		<td> <div class="button-group"> 
      			<a class="button border-main" href="assignlist.jsp?cid=<%=c.getCourseId()%>"><span class="icon-edit"></span>作业管理</a> </div> 
      		</td>
      	</tr>
      	<%
      }
       %>
    </table>
  </div>
</form>
<script type="text/javascript">



</script>
</body>
</html>