<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0072)http://127.0.0.1:8080/jspbbs/post.jsp -->
<HTML>
<HEAD>
<TITLE>斑马学员论坛--发布帖子</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="css/style.css">
<SCRIPT type=text/javascript>
	function check() {
		if (document.postForm.title.value == "") {
			alert("标题不能为空");
			return false;
		}
		if (document.postForm.content.value == "") {
			alert("内容不能为空");
			return false;
		}
		if (document.postForm.content.value.length > 1000) {
			alert("长度不能大于1000");
			return false;
		}
	}
</SCRIPT>

<META name=GENERATOR content="MSHTML 8.00.6001.18783">
</HEAD>
<BODY>
	<%@ include file="include/head.jsp"%>
	<!--      主体        -->
	<DIV>
		<BR>
		<!--      导航        -->
		<DIV>
			&gt;&gt;<B><A href="index">论坛首页</A></B>&gt;&gt; <B><A
				href="list?bid=${bk.bid }">${bk.bname }</A></B>
		</DIV>
		<BR>
		<DIV>
			<FORM onsubmit="return check()" method=post name=postForm
				action="fatie">
				<INPUT value="${bk.bid }" type=hidden name="bid"> 
				
				<DIV class=t>
					<TABLE cellSpacing=0 cellPadding=0 align=center>
						<TBODY>
							<TR>
								<TD class=h colSpan=3><B>发表帖子</B></TD>
							</TR>
							<TR class=tr3>
								<TH width="20%"><B>标题</B></TH>
								<TH><INPUT style="PADDING-LEFT: 2px; FONT: 14px Tahoma"
									class=input tabIndex=1 size=60 name=title></TH>
							</TR>
							<TR class=tr3>
								<TH vAlign=top>
									<DIV>
										<B>内容</B>
									</DIV>
								</TH>
								<TH colSpan=2>
									<DIV>
										<SPAN><TEXTAREA style="WIDTH: 500px" tabIndex=2 rows=20
												cols=90 name=content></TEXTAREA></SPAN>
									</DIV>(不能大于:<FONT color=blue>1000</FONT>字)
								</TH>
							</TR>
						</TBODY>
					</TABLE>
				</DIV>
				<DIV style="TEXT-ALIGN: center; MARGIN: 15px 0px">
					<INPUT class=btn tabIndex=3 value="提 交" type=submit> <INPUT
						class=btn tabIndex=4 value="重 置" type=reset>
				</DIV>
			</FORM>
		</DIV>
	</DIV>
	<!--      声明        -->
	<BR>
	<%@ include file="include/foot.jsp"%>
</BODY>
</HTML>
