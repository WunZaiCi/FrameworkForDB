package com.banma.BF.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banma.BF.dao.TieZiDao;
import com.banma.BF.entity.TieZi;
import com.banma.BF.entity.User;

/**
 * 删除帖子的功能 Servlet implementation class DeleteTieziServlet
 */
@WebServlet({ "/DeleteTieziServlet", "/deleteTiezi" })
public class DeleteTieziServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TieZiDao tieZiDao = new TieZiDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		String tidstr = request.getParameter("tid");
		if (tidstr == null || tidstr.trim().isEmpty()) {
			// 错误提示
			request.setAttribute("msg", "没有找到您想要的帖子信息<a href=\"index\">>>>>去首页</a>");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			return;
		}
		int tid = Integer.valueOf(tidstr);
		TieZi tieZi = tieZiDao.getTieziByTid(tid);

		if (tieZi == null) {
			// 错误提示
			request.setAttribute("msg", "没有找到您想要的帖子信息<a href=\"index\">>>>>去首页</a>");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			return;
		}

		// 判断是否为登陆状态
		User loginUser = (User) request.getSession().getAttribute("user");
		if (loginUser == null) {
			// 错误提示
			request.setAttribute("msg", "请先登录<a href=\"login\">>>>>去登陆</a>");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			return;
		}

		// 判断帖子发帖人是否和当前登陆用户一样
		if (tieZi.getUid() != loginUser.getUid()) {
			// 错误提示
			request.setAttribute("msg", "您没有删除权限<a href=\"tiezi?tid=" + tieZi.getTid() + "\">>>>>去帖子详情页</a>");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			return;
		}

		if (tieZiDao.deleteTiezi(tid)) {// 如果删除帖子成功
			// 删除后 重定向到该帖子原属板块
			response.sendRedirect("list?bid=" + tieZi.getBid());
		} else {
			// 错误提示
			request.setAttribute("msg", "删除失败，请重试<a href=\"tiezi?tid=" + tieZi.getTid() + "\">>>>>去帖子详情页</a>");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			return;
		}

	}
}
