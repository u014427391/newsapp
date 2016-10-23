<%@page import="com.newsService.utils.URLUtiluty"%>
<%@page import="com.newsService.po.Comment"%>
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
	List<Comment> list = (ArrayList<Comment>)session.getAttribute("comments");
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
                <li><a href="<%=URLUtiluty.COMMENT_MANAGER_SERVLETURL %>"class="icon-file-text"> 评论管理</a>
                </li>
                <li><a href="#" class="icon-file"> 文件</a></li>
                <li><a href="#" class="icon-th-list"> 栏目</a></li>
            </ul>
        </div>
        <div class="admin-bread">
            <span>您好，admin，欢迎您的光临。</span>
            <ul class="bread">
                <li><a href="index.jsp" class="icon-home"> 开始</a></li>
                <li><a href="<%=URLUtiluty.COMMENT_MANAGER_SERVLETURL %>">评论管理</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="admin">
	<form method="post">
    <div class="panel admin-panel">
    	<div class="panel-head"><strong>评论信息列表</strong></div>
        <div class="padding border-bottom">
            <input type="button" class="button button-small checkall" name="checkall" checkfor="id" value="全选" />
            <input type="button" class="button button-small border-green" value="添加信息" />
            <input type="button" class="button button-small border-yellow" value="批量删除数据" />
            <input type="button" class="button button-small border-blue" value="回收站" />
        </div>
        <table class="table table-hover">
			<tr>
        		<th width="45">选择</th>
        		<th width="120">ID号</th>
        		<th width="120">新闻ID号</th>
        		<th width="120">评论时间</th>
        		<th width="120">评论内容</th>
        		<th width="120">评论人地区</th>
        		<th width="120">支持人数</th>
        		<th width="120">反对人数</th>
        		<th width="120">操作</th>
        	</tr>
        	<%
				if(list!=null&&list.size()>0){
					 for(int i=0;i<list.size();i++){
			%>
            <tr>
           	 	<td><input type="checkbox" name="id" value="1" /></td>
            	<td><%=list.get(i).getCid() %></td>
            	<td><%=list.get(i).getNid() %></td>
            	<td><%=list.get(i).getPtime() %></td>
            	<td><%=list.get(i).getContent() %></td>
            	<td><%=list.get(i).getRegion() %></td>
            	<td><%=list.get(i).getSupportCount() %></td>
            	<td><%=list.get(i).getOpposeCount() %></td>
            	<td>
            		<a href="<%=basePath %>servlet/DeleteCommentServlet?cid=<%=list.get(i).getCid() %>" onclick="{if(confirm('确认删除?')){return true;}return false;}" class="button border-blue button-little">删除</a>
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