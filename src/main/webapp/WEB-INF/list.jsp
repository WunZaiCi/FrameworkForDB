<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0054)http://127.0.0.1:8080/jspbbs/list.jsp -->
<HTML>
<HEAD>
<TITLE>斑马学员论坛--帖子列表</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="css/style.css">
<META name=GENERATOR content="MSHTML 8.00.6001.18783">
</HEAD>
<BODY>
	
	<%@ include file="include/head.jsp"%>
	<!--      主体        -->
	<DIV>
		<!--      导航        -->
		<BR>
		<DIV>
			&gt;&gt;<B><A href="index">论坛首页</A></B>&gt;&gt;
			<B><A
				href="list?bid=${vo.banKuai.bid }">${vo.banKuai.bname }</A></B>
		</DIV>
		<BR>
		<!--      新帖        -->
		<DIV>
			<A
				href="fatie?bid=${vo.banKuai.bid }"><IMG
				border=0 src="images/post.gif"></A>
		</DIV>
		<!--         翻 页         -->
		<DIV>
			<A href="list?bid=${vo.banKuai.bid }&page=${prePageIndex }">上一页</A>|
			<A href="list?bid=${vo.banKuai.bid }&page=${nextPageIndex }">下一页</A>
		</DIV>
		<DIV class=t>
			<TABLE cellSpacing=0 cellPadding=0 width="100%">
				<TBODY>
					<TR>
						<TH style="WIDTH: 100%" class=h colSpan=4><SPAN>&nbsp;</SPAN></TH>
					</TR>
					<!--       表 头           -->
					<TR class=tr2>
						<TD>&nbsp;</TD>
						<TD style="WIDTH: 80%" align=middle>文章</TD>
						<TD style="WIDTH: 10%" align=middle>作者</TD>
						<TD style="WIDTH: 10%" align=middle>回复</TD>
					</TR>
					
					 
					<!--         主 题 列 表        -->
					<c:forEach items="${vo.tiezi }" var="tvo">
					<TR class=tr3>
						<TD><IMG border=0 src="images/topic.gif"></TD>
						<TD style="FONT-SIZE: 15px"><A
							href="tiezi?tid=${tvo.tid }">${tvo.title }
						</A></TD>
						<TD align=middle>${tvo.username }</TD>
						<TD align=middle>${tvo.huiFuCount }</TD>
					</TR>
					</c:forEach>
				
				</TBODY>
			</TABLE>
			<c:if test="${empty vo.tiezi }">
				<center>暂时还没有帖子</center>
			</c:if>
			
		</DIV>
		<!--            翻 页          -->
		<DIV>
			<A href="list?bid=${vo.banKuai.bid }&page=${prePageIndex }">上一页</A>|
			<A href="list?bid=${vo.banKuai.bid }&page=${nextPageIndex }">下一页</A>
		</DIV>
	</DIV>
	<!--             声 明          -->
	<BR>
	<%@ include file="include/foot.jsp" %>
</BODY>
</HTML>
