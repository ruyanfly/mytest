<?xml version="1.0" encoding="UTF-8" ?>
<!--<?xml version="1.0" encoding="ISO-8859-1" ?>-->
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<!--<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1" session="false"/>-->
	<jsp:directive.page contentType="text/html; charset=UTF-8" 
		pageEncoding="UTF-8" session="false"/>
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="true" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Insert title here</title>
</head>
<body>
<h1>This is 1 JSP!</h1>
<a href="/servlet/index.jsp">跳转到首页</a>
<h1>或者换种表示</h1>
<a href="${pageContext.request.contextPath}/index.jsp">跳转到首页</a>
<h1>或者使用from</h1>
<!-- ${pageContext.request.contextPath}的效果等同于request.getContextPath()，两者获取到的都是"/项目名称" -->
<!-- <form action="${pageContext.request.contextPath}/index.jsp" method="post"> -->
<!-- 这里要跳转页面所以应该是wepapps的路径加上页面的位置 -->
<form action="/servlet/index.jsp" mothod="post">
<input type="submit"  value="提交"/>
</form>
</body>
</html>
</jsp:root>