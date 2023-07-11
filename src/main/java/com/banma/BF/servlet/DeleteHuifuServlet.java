package com.banma.BF.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banma.BF.dao.HuifuDao;
import com.banma.BF.entity.HuiFu;
import com.banma.BF.entity.User;

/**
 * Servlet implementation class DeleteHuifuServlet
 */
@WebServlet({"/DeleteHuifuServlet","/deleteHuifu"})
public class DeleteHuifuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HuifuDao huifuDao = new HuifuDao(); 


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=UTF-8");

			String hidstr = request.getParameter("hid");
			if (hidstr == null || hidstr.trim().isEmpty()) {
				// 错误提示
				request.setAttribute("msg", "没有找到您想要的回复信息<a href=\"tiezi\">>>>>去帖子详情页</a>");
				request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
				return;
			}
			int hid = Integer.valueOf(hidstr);
			
			
			String tidstr = request.getParameter("tid");
			if (tidstr == null || tidstr.trim().isEmpty()) {
				// 错误提示
				request.setAttribute("msg", "没有找到您想要的帖子信息<a href=\"tiezi\">>>>>去帖子详情页</a>");
				request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
				return;
			}
			int tid = Integer.valueOf(tidstr);
			
			
			
			HuiFu huifu = huifuDao.getHuifuByHid(hid);
			
			if (huifu == null) {
				// 错误提示
				request.setAttribute("msg", "没有找到您想要的回复信息<a href=\"tiezi?tid=" + tid+"\">>>>>去帖子详情页</a>");
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

			// 判断回复的回复人是否和当前登陆用户一样
			if (huifu.getUid() != loginUser.getUid()) {
				// 错误提示
				request.setAttribute("msg", "您没有删除权限<a href=\"tiezi?tid=" + huifu.getTid() + "\">>>>>去帖子详情页</a>");
				request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
				return;
			}

			if (huifuDao.deleteHuifu(hid)) {// 如果删除帖子成功
				// 删除后 重定向到该帖子页
				response.sendRedirect("tiezi?tid=" + huifu.getTid());
			} else {
				// 错误提示
				request.setAttribute("msg", "删除失败，请重试<a href=\"tiezi?tid=" + huifu.getTid() + "\">>>>>去帖子详情页</a>");
				request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
				return;
			}
	}



}
