<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">

<HTML>
<HEAD>
<TITLE>斑马学员论坛--登录</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="css/style.css">
<SCRIPT language=javascript>
	function check() {
		if (document.loginForm.uName.value == "") {
			alert("用户名不能为空");
			return false;
		}
		if (document.loginForm.uPass.value == "") {
			alert("密码不能为空");
			return false;
		}
	}
</SCRIPT>

<META name=GENERATOR content="MSHTML 8.00.6001.18783">
</HEAD>
<BODY>
	<%@ include file="include/head.jsp"%>
	<!--      导航        -->
	<DIV>
		&gt;&gt;<B><A href="http://127.0.0.1:8080/jspbbs/index.jsp">论坛首页</A></B>
	</DIV>
	<!--      用户登录表单        -->
	<DIV style="MARGIN-TOP: 15px" class=t align=center>
		<FORM  onsubmit="return check()" method=post name=loginForm
			action="login"/>
			<BR>
			${msg }
			用户名 &nbsp;<INPUT class=input tabIndex=1 maxLength=20 size=35
				type=text name=uName> <BR>密 码 &nbsp;<INPUT class=input
				tabIndex=2 maxLength=20 size=40 type=password name=uPass> <BR>
			<INPUT class=btn tabIndex=6 value="登 录" type=submit>
		</FORM>
	</DIV>
	<!--      声明        -->
	<BR>
	<%@ include file="include/foot.jsp" %>
</BODY>
</HTML>
