<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">

<HTML>
<HEAD>
<TITLE>欢迎访问斑马学员论坛</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="css/style.css">
<META name=GENERATOR content="MSHTML 8.00.6001.18783">
</HEAD>
<BODY>
	<DIV>

		<A href="index"> <IMG src="images/logo.png" width="123px;"
			height="45px;">
		</A>
	</DIV>
	<!--      用户信息、登录、注册        -->

	<c:choose>
		<c:when test="${not empty sessionScope.user }">
			<DIV class=h>

				您好：${sessionScope.user.username} &nbsp;| &nbsp; <A href="logout">注销</A>
			</DIV>
		</c:when>
		
		<c:otherwise>
			<DIV class=h>

				您尚未 <A href="login">登录</A> &nbsp;| &nbsp; 
				<A href="register">注册</A>|
			</DIV>
			
			
		</c:otherwise>
	</c:choose>
	<BR>

</BODY>
</HTML>
