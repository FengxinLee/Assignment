<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="pojo.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String user = (String)session.getAttribute("userName");
int aid = Integer.parseInt(request.getParameter("aid"));
Assign a = AssignDao.getAssign(aid);
Answer an = AnswerDao.findAnswer(aid, user);
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
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span> 浏览作业</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" 
    action="<%=request.getContextPath()%>/addanswer?anid=<%=an.getAnswerId()%>&mark=<%=an.getAnswerMark()%>&aid=<%=aid %>">  
      <div class="form-group">
        <div class="label">
          <label>作业内容：</label>
        </div>
        <div class="field">
          <label style="line-height:33px;"><%=a.getAssignContent() %></label>
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label>评分：</label>
        </div>
        <div class="field">
          <label style="line-height:33px;"><%=an.getAnswerMark() == -1 ? "教师未评阅" : ""+an.getAnswerMark() %></label>
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label>我的答案：</label>
        </div>
        <div class="field">
          <textarea name="note" class="input" style=" height:90px;" placeholder="<%=an.getAnswerContent() %>"></textarea>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit">提交答案</button>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label>参考答案：</label>
        </div>
        <div class="field">
          <textarea readonly="readonly" class="input" style=" height:90px;" 
          placeholder="<%=an.getAnswerMark() == -1 ? "":a.getAssignAnswer() %>"></textarea>
          <div class="tips"></div>
        </div>
      </div>
      
    </form>
    <%
    String mess=(String)session.getAttribute("messageuser");
    if("".equals(mess) || mess==null || mess.equals("null")){ }
 	else{%>
    <script type="text/javascript">
        alert("<%=mess%>");
	</script>
 	 <% 
    	session.setAttribute("messageuser", "");
    }
    %>
    
    
  </div>
</div>
</body></html>