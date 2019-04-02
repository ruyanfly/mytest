<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<jsp:directive.page contentType="text/html; charset=UTF-8" 
		pageEncoding="UTF-8" session="false"/>
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="true" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>HTML的FORM表单元素</title>
</head>
<body>
<FIELDSET style="width:500px;">
<LEGEND>HTML的FORM表单元素</LEGEND>
<!--form表单的action属性规定当提交表单时，向何处发送表单数据，method属性指明表单的提交方式，分为get和post，默认为get-->
<FORM action="${pageContext.request.contextPath}/ServletTest" method="post">
<!--输入文本框，SIZE表示显示长度，maxlength表示最多输入长度-->
编&amp;nbsp;&amp;nbsp;号(文本框): 
<INPUT type="text" name="userid" value="NO." size="2" maxlength="2"/><BR />
<!--输入文本框，通过value指定其显示的默认值-->
用户名(文本框): <INPUT type="text" name="username" value="请输入用户名"/><BR />
<!--密码框，其中所有输入的内容都以密文的形式显示-->
密&amp;nbsp;&amp;nbsp;码(密码框): <!--&nbsp;表示的是一个空格-->
<INPUT type="password" name="userpass" value="请输入密码"/><BR />
<!--单选按钮，通过checked指定默认选中，名称必须一样，其中value为真正需要的内容-->
性&amp;nbsp;&amp;nbsp;别(单选框):
<INPUT type="radio" name="sex" value="男" checked="true"/>男
<INPUT type="radio" name="sex" value="女" />女<BR />
<!--下拉列表框，通过<option>元素指定下拉的选项-->
部&amp;nbsp;&amp;nbsp;门(下拉框):
<SELECT>
<OPTION value="技术部"/>技术部
<OPTION value="销售部" selected="true"/>销售部
<OPTION value="财务部"/>财务部
</SELECT><BR />
<!--复选框，可以同时选择多个选项，名称必须一样，其中value为真正需要的内容-->
兴&amp;nbsp;&amp;nbsp;趣(复选框):
<INPUT type="checkbox" name="inst" value="唱歌"/>唱歌
<INPUT type="checkbox" name="inst" value="游泳"/>游泳
<INPUT type="checkbox" name="inst" value="跳舞"/>跳舞
<INPUT type="checkbox" name="inst" value="编程" checked="true"/>编程
<INPUT type="checkbox" name="inst" value="上网"/>上网 <BR />
<!--大文本输入框，宽度为34列，高度为5行-->
说&amp;nbsp;&amp;nbsp;明(文本域): 
<TEXTAREA name="note" rows="5" cols="34">
</TEXTAREA><BR />
<!--隐藏域，在页面上无法看到，专门用来传递参数或者保存参数-->
<INPUT type="hidden" name="hiddenField" value="hiddenvalue"/>
<!--提交表单按钮，当点击提交后，所有填写的表单内容都会被传输到服务器端-->
<INPUT type="submit" value="提交(提交按钮)"/>
<!--重置表单按钮，当点击重置后，所有表单恢复原始显示内容-->
<INPUT type="reset" value="重置(重置按钮)"/>
</FORM>
<!--表单结束-->
</FIELDSET>
</body>
</html>
</jsp:root>