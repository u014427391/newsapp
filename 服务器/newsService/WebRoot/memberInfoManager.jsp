<%@page import="com.newsService.po.Member"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>后台管理</title>
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery-1.11.0.js"></script>
    <script src="js/index.js"></script>
    <script src="js/respond.js"></script>
    <script src="js/admin.js"></script>
    <link type="image/x-icon" href="/favicon.ico" rel="shortcut icon" />
    <link href="/favicon.ico" rel="bookmark icon" />
</head>

<body>
<%
	  List<Member> list=(ArrayList<Member>)session.getAttribute("membersInfo");
	
%>
<div class="lefter">
    <div class="logo"><a href="#" target="_blank"><img src="images/logo.png" alt="后台管理系统" /></a></div>
</div>
<div class="righter nav-navicon" id="admin-nav">
    <div class="mainer">
        <div class="admin-navbar">
            <span class="float-right">
            	<a class="button button-little bg-main" href="#">前台首页</a>
                <a class="button button-little bg-yellow" href="login.jsp">注销登录</a>
            </span>
            <ul class="nav nav-inline admin-nav">
                <li><a href="index.jsp" class="icon-home"> 开始</a>
                </li>
                <li><a href="systemManager.jsp" class="icon-cog"> 系统</a>
                </li>
                <li><a href="#" class="icon-file"> 文件</a></li>
                <li><a href="#" class="icon-th-list"> 栏目</a></li>
            </ul>
        </div>
        <div class="admin-bread">
            <span>您好，admin，欢迎您的光临。</span>
            <ul class="bread">
                <li><a href="index.jsp" class="icon-home"> 开始</a></li>
                <li><a href="<%=basePath %>servlet/GetMemberInfoServlet">会员信息管理</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="admin">
	<form method="post">
    <div class="panel admin-panel">
    	<div class="panel-head"><strong>会员信息列表</strong></div>
        <div class="padding border-bottom">
            <input type="button" class="button button-small checkall" name="checkall" checkfor="id" value="全选" />
            <input type="button" class="button button-small border-green" value="添加信息" />
            <input type="button" class="button button-small border-yellow" value="批量删除数据" />
            <input type="button" class="button button-small border-blue" value="回收站" />
        </div>
        <table class="table table-hover">
        	<tr>
        		<th width="45">选择</th>
        		<th width="120">账号</th>
        		<th width="1"></th>
        		<th width="120">密码</th>
        		<th width="120">积分</th>
        		<th width="120">等级</th>
        		<th width="120">账号状态</th>
        	</tr>
        	<%
				if(list!=null&&list.size()>0){
					 for(int i=0;i<list.size();i++){
			%>
            <tr>
           	 	<td><input type="checkbox" name="id" value="1" /></td>
            	<td><%=list.get(i).getAccount() %></td>
            	<td><input type="hidden" name="account" value="<%=list.get(i).getAccount() %>"/></td>
				<td><%=list.get(i).getPassword() %></td>
				<td><%=list.get(i).getRank() %></td>
				<td><%=list.get(i).getCredits() %></td>
				<% 
				   if(list.get(i).getAccIsValid().equals("1")){
				%>
				     <td>开启</td>
				     <td><input type="hidden" name="accIsValid" value="<%=list.get(i).getAccIsValid() %>"/></td>
				<%
				   }else{
				 %>				
				     <td>已被封号</td>
				     <td><input type="hidden" name="accIsValid" value="<%=list.get(i).getAccIsValid() %>"/></td>
				 <%
				 	}
				  %>
            	<td>
            		<a href="<%=basePath %>servlet/UpdateMemberInfoServlet?account=<%=list.get(i).getAccount() %>&accIsValid=0" class="button border-blue button-little"onclick="{if(confirm('开启成功！')){return true;}return false;}">开启</a>
				    <a href="<%=basePath %>servlet/UpdateMemberInfoServlet?account=<%=list.get(i).getAccount() %>&accIsValid=1" class="button border-green button-little" onclick="{if(confirm('确认封号?')){return true;}return false;}">封号</a>
				</td>
            </tr>
            <%
            		}
               }
             %>
        </table>
        <div class="panel-foot text-center">
            <ul class="pagination"><li><a href="#">上一页</a></li></ul>
            <ul class="pagination pagination-group">
                <li><a href="#">1</a></li>
                <li class="active"><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
            </ul>
            <ul class="pagination"><li><a href="#">下一页</a></li></ul>
        </div>
    </div>
    </form>
    <br />
</div>

</body>
</html>