<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0067)http://127.0.0.1:8080/jspbbs/detail.jsp -->
<HTML>
<HEAD>
<TITLE>斑马学员论坛--看贴</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="css/style.css">
<META name=GENERATOR content="MSHTML 8.00.6001.18783">
</HEAD>
<script type="text/javascript">
	function deleteTiezi(tid) {
		var result = confirm("你確定要刪除嗎");
		if (result) {
			location.href = "deleteTiezi?tid=" + tid;
		}
	}
	
	function deleteHuifu(hid,tid) {
		var result = confirm("你確定要刪除嗎");
		if (result) {
			location.href = "deleteHuifu?hid=" + hid+"&tid="+tid;
		}
	}
</script>

<BODY>
	<%@ include file="include/head.jsp"%>
	<!--      主体        -->
	<DIV>
		<BR>
		<!--      导航        -->
		<DIV>
			&gt;&gt;<B><A href="index">论坛首页</A></B>&gt;&gt; <B><A
				href="list?bid=${vo.banKuai.bid }">${vo.banKuai.bname }</A></B>
		</DIV>
		<BR>
		<!--      回复、新帖        -->
		<DIV>
			<A href="huitie?tid=${vo.tiezi.tid }"><IMG border=0
				src="images/reply.gif"></A> 
			<A href="fatie?bid=${vo.banKuai.bid }"><IMG	border=0 src="images/post.gif"></A>
		</DIV>
		<!--         翻 页         -->
		<DIV>
			<A href="tiezi?tid=${vo.tiezi.tid }&page=${prePageIndex }">上一页</A>|
			<A href="tiezi?tid=${vo.tiezi.tid }&page=${nextPageIndex }">下一页</A>
		</DIV>
		<!--      本页主题的标题        -->
		<DIV>
			<TABLE cellSpacing=0 cellPadding=0 width="100%">
				<TBODY>
					<TR>
						<TH class=h>本页主题: ${vo.tiezi.title }</TH>
					</TR>
					<TR class=tr2>
						<TD>&nbsp;</TD>
					</TR>
				</TBODY>
			</TABLE>
		</DIV>
		<!--      主题        -->
		<DIV class=t>
			<TABLE style="BORDER-TOP-WIDTH: 0px; TABLE-LAYOUT: fixed"
				cellSpacing=0 cellPadding=0 width="100%">
				<TBODY>
					<TR class=tr1>
						<TH style="WIDTH: 20%"><B>${vo.user.username }</B><BR> <IMG
							src="images/${vo.user.headImage }"><BR>注册:${vo.user.createTime }<BR></TH>
						<TH>
							<H4>${vo.tiezi.title }</H4>
							<DIV>
								<PRE>${vo.tiezi.content }</PRE>
							</DIV>
							<DIV class="tipad gray">
								发表：[${vo.tiezi.createTime }] &nbsp; 最后修改:[${vo.tiezi.updateTime }]


								<c:if test="${not empty user and user.uid == vo.user.uid}">
									<A href="javascript:deleteTiezi(${vo.tiezi.tid })">[删除]</A>
									<A href="updateTiezi?tid=${vo.tiezi.tid }">[修改]</A>
								</c:if>

							</DIV>
						</TH>
					</TR>
				</TBODY>
			</TABLE>
		</DIV>
		<!--      回复        -->
		<c:forEach items="${vo.list }" var="hvo">
			<DIV class=t>
				<TABLE style="BORDER-TOP-WIDTH: 0px; TABLE-LAYOUT: fixed"
					cellSpacing=0 cellPadding=0 width="100%">
					<TBODY>
						<TR class=tr1>
							<TH style="WIDTH: 20%"><B>${hvo.user.username }</B><BR>
								<BR> <IMG src="images/${hvo.user.headImage }"><BR>注册:${hvo.user.createTime }<BR></TH>
							<TH>
								<H4>re：</H4>
								<DIV>
									<PRE>${hvo.huifu.content }</PRE>
								</DIV>
								<DIV class="tipad gray">
									发表：[${hvo.huifu.createTime }] &nbsp; 最后修改:[${hvo.huifu.updateTime }]

									<c:if test="${not empty user and user.uid == hvo.user.uid}">
										<A href="javascript:deleteHuifu(${hvo.huifu.hid },${vo.tiezi.tid })">[删除]</A>
										<A href="updateHuifu?hid=${hvo.huifu.hid }&tid=${vo.tiezi.tid }">[修改]</A>
									</c:if>
								</DIV>
							</TH>
						</TR>
					</TBODY>
				</TABLE>
			</DIV>
		</c:forEach>
		<DIV>
			<A href="tiezi?tid=${vo.tiezi.tid }&page=${prePageIndex }">上一页</A>|
			<A href="tiezi?tid=${vo.tiezi.tid }&page=${nextPageIndex }">下一页</A>
		</DIV>
	</DIV>
	<!--      声明        -->
	<BR>
	<%@ include file="include/foot.jsp"%>
</BODY>
</HTML>
