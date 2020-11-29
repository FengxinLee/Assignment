<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="pojo.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

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
  <%
  int cid = Integer.parseInt(request.getParameter("cid"));
  String cname = CourseDao.getCourseName(cid).getCourseName();
  ArrayList<Assign> as = AssignDao.getAllAssign(cid);
  %>
    <div class="panel-head"><strong class="icon-reorder"> <%=cname %> -- 作业列表</strong> <a href="" style="float:right; display:none;"> 添加字段</a></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <li> <a class="button border-main icon-plus-square-o" href="assignadd.jsp?cid=<%=cid%>"> 布置作业</a> </li>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th>作业ID</th>
        <th>作业内容</th>
        <th>参考答案</th>
        <th width="310">操作</th>
      </tr>
      <%
      for(Assign a: as)
      {
      	%> 
      	<tr>
      		<td> <%= a.getAssignId() %> </td>
      		<td> <%= a.getAssignContent() %> </td>
      		<td> <%= a.getAssignAnswer() %> </td>
      		<td> <div class="button-group"> 
      			<a class="button border-main" href="answerlist.jsp?aid=<%=a.getAssignId()%>&cid=<%=cid%>"><span class="icon-edit"></span>批阅作业</a> </div> 
      		</td>
      	</tr>
      	<%
      }
       %>
    </table>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <li> <a class="button border-main" href="list.jsp"> 返回全部课程</a> </li>
      </ul>
    </div>
  </div>
</form>
    <%
    String mess=(String)session.getAttribute("messageaddass");
    if("".equals(mess) || mess==null || mess.equals("null")){ }
 	else{%>
    <script type="text/javascript">
        alert("<%=mess%>");
	</script>
 	 <% 
    	session.setAttribute("messageaddass", "");
    }
    %>
<script type="text/javascript">



</script>
</body>
</html>