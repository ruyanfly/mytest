<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Insert title here</title>
<SCRIPT type="text/javascript">
//刷新验证码
function changeImg(){
	//alert("this is alert!"+": path : "+"${pageContext.request.contextPath}");
	//这里路径容易出问题，要与web.xml中的url-pattern一致才行
	document.getElementById("validateCodeImg").src="${pageContext.request.contextPath}/DrawImage?"+Math.random();
}
</SCRIPT>
</head>
<body>
<h1>This is change!</h1>
<FORM action="${pageContext.request.contextPath}/CheckServlet" method="post">
验证码：<input type="text" name="validateCode"/>
<IMG alt="验证码看不清, 换一张" src="${pageContext.request.contextPath}/DrawImage" id="validateCodeImg" onclick="changeImg()"/>
<!--<a href="javascript:void(0)" onclick="changeImg()">看不清, 换一张</a>-->
<BR />
<INPUT type="submit" value="提交"/>
</FORM>
</body>
</html>