<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="pojo.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String user = (String)session.getAttribute("userName");


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
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span> 新建作业</strong></div>
  <div class="body-content">
    <%
  int cid = Integer.parseInt(request.getParameter("cid"));
  String cname = CourseDao.getCourseName(cid).getCourseName();
  %>
    <form method="post" class="form-x" action="<%=request.getContextPath()%>/addassign?cid=<%=cid%>">  
      <div class="form-group">
        <div class="label">
          <label>课程：</label>
        </div>
        <div class="field">
              <label  style="line-height:33px;"><%=cname %></label>
       </div>
      </div>

      <div class="form-group">
        <div class="label">
          <label>作业内容：</label>
        </div>
        <div class="field">
          <textarea name="note" class="input" style=" height:90px;" data-validate="required:必须输入作业内容"></textarea>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>参考答案：</label>
        </div>
        <div class="field">
          <textarea name="answer" class="input" style=" height:90px;" data-validate="required:必须输入参考答案"></textarea>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit">提交作业</button>
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
    
    
  </div>
</div>

</body></html>