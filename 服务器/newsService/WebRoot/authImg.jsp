<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.Color"%>
<%@page import="java.awt.Graphics"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@ page language="java" import="java.util.*" contentType="image/JPEG" pageEncoding="UTF-8"%>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>生成验证码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
 
    <%!   
	Color getRandColor(int fc,int bc){
		Random random=new Random();
		if(fc>255) fc=255;
		if(bc>255) bc=255;
		int r=fc+random.nextInt(bc-fc);
		int g=fc+random.nextInt(bc-fc);
		int b=fc+random.nextInt(bc-fc);
		return new Color(r,g,b);
	}
	
	private String getRandomChar(){
		
		int rand = (int) Math.round(Math.random()*2);
		
		long itmp = 0;
		char ctmp = '\u0000';
		
		switch(rand){
		case 1:
			itmp = Math.round(Math.random()*25+65);
			ctmp = (char)itmp;
			return String.valueOf(ctmp);
		case 2:
			itmp = Math.round(Math.random()*25+65);
			ctmp = (char)itmp;
			return String.valueOf(ctmp);
		default:
			itmp = Math.round(Math.random()*9);
			return String.valueOf(itmp);
		}
	
	}
 %>	
	
<% 	
	
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		int width=70,height=20;
		
		BufferedImage image=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(getRandColor(200, 250)); 
        g.fillRect(1, 1, width - 1, height - 1);  
        g.setColor(new Color(102, 102, 102));  
        g.drawRect(0, 0, width - 1, height - 1); 
		
		g.setColor(getRandColor(160, 200));
		
		
		
		
		/*
		for(int i=0;i<160;i++){
			int x = random.nextInt(width-1);
			int y = random.nextInt(height-1);
			int x1 = random.nextInt(6) + 1;
			int y1 = random.nextInt(12) + 1;
			g.drawLine(x, y, x+x1, y+y1);
		}
		
		
		for(int i=0;i<70;i++){
			int x = random.nextInt(width-1);
			int y = random.nextInt(height-1);
			int x1 = random.nextInt(6) + 1;
			int y1 = random.nextInt(12) + 1;
			g.drawLine(x, y, x+x1, y+y1);
		}*/
		
	
		String sRand="";
		for(int i=0;i<4;i++){
			String tmp=getRandomChar();
			sRand += tmp;
			g.setColor(Color.red);
			g.drawString(tmp, 15*i+10, 15);
		}
		
		session.setAttribute("rand", sRand);
		g.dispose();
		
		ImageIO.write(image, "JPEG", response.getOutputStream());
	
		response.getOutputStream().flush();    
   		response.getOutputStream().close();    
   		response.flushBuffer();    
   		out.clear();    
   		out = pageContext.pushBody();   
    
 %>
  </body>
</html>
