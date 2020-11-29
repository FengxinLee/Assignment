<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="pojo.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String user = (String)session.getAttribute("userName");


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
<%
  int aid = Integer.parseInt(request.getParameter("aid"));
  int cid = Integer.parseInt(request.getParameter("cid"));
  ArrayList<Answer> ans = AnswerDao.getAllAnswer(aid);

%>

  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> AssignId:<%=aid %> -- 提交列表</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
   
    <table class="table table-hover text-center">
      <tr>
        <th>提交ID</th>
        <th>学号</th>
        <th>提交内容</th>
        <th>分数</th>
        <th width="310">操作</th>
      </tr>
      <%
      for(Answer a : ans)
      {
      	%> 
      	<tr>
      	<form method="post"  id="listform" 
      	action="addmark?anid=<%=a.getAnswerId()%>&cid=<%=cid%>&aid=<%=aid%>&isw=<%=a.getAnswerIswrite()%>">
      		<td> <%=a.getAnswerId() %> </td>
      		<td> <%=a.getUserId() %> </td>
      		<td> <%=a.getAnswerIswrite()==-1 ? "未提交" : a.getAnswerContent() %> </td>
      		<td> <input name="title" class="input w60" type="number" min="0" max="10" data-validate="required:请输入分数"  
      			value="<%= a.getAnswerMark()==-1 ? 0 : a.getAnswerMark()%>" />
      		</td>
      		<td>
      			<button class="button border-main" type="submit"><span class="icon-edit"></span> 提交分数</button>
      		</td>
      	</form>
      	</tr>
      	<%
      }
       %>
    </table>
     <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <li> <a class="button border-main" href="assignlist.jsp?cid=<%=cid%>"> 返回全部作业</a> </li>
      </ul>
    </div>
  </div>



<%
String mess=(String)session.getAttribute("messageaddans");
if("".equals(mess) || mess==null || mess.equals("null")){ }
	else{%>
<script type="text/javascript">
    alert("<%=mess%>");
</script>
	 <% 
	session.setAttribute("messageaddans", "");
}
%>

</body>
</html>