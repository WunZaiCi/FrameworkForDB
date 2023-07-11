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
	<%@ include file="include/head.jsp"%>


	<DIV>
		<!--      主体        -->
		<DIV class=t>
			<TABLE cellSpacing=0 cellPadding=0 width="100%">
				<TBODY>
					<TR class=tr2 align=middle>
						<TD colSpan=2>论坛</TD>
						<TD style="WIDTH: 5%">主题</TD>
						<TD style="WIDTH: 25%">最后发表</TD>
					</TR>

					<!--       主版块       -->
					<c:forEach items="${indexVO.banKuais }" var="bk">
						<TR class=tr3>
							<TD colSpan=4>${bk.bname }</TD>
						</TR>

						<!--       子版块       -->
						<c:forEach items="${bk.cList }" var="cbk">
							<TR class=tr3>
								<TD width="5%">&nbsp;</TD>
								<TH align=left><IMG src="images/board.gif"> <A
									href="list?bid=${cbk.bid }">${cbk.bname }</A></TH>
								<TD align=middle>${cbk.tieCount }</TD>


								<c:choose>
									<c:when test="${not empty cbk.lastTieZi }">
										<TH><SPAN><A href="tiezi?tid=${cbk.lastTieZi.tid }">${cbk.lastTieZi.title }
											</A></SPAN><BR> <SPAN>${cbk.lastTieZi.username }</SPAN> <SPAN
											class=gray>[ ${cbk.lastTieZi.updateTime } ]</SPAN></TH>

									</c:when>

									<c:otherwise>
										<TH><SPAN>还没有帖子</SPAN></TH>


									</c:otherwise>
								</c:choose>

							</TR>
						</c:forEach>
					</c:forEach>
				</TBODY>
			</TABLE>
		</DIV>
	</DIV>
	<BR>
	<%@ include file="include/foot.jsp"%>
</BODY>
</HTML>
