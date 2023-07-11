package com.banma.BF.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banma.BF.dao.BanKuaiDao;
import com.banma.BF.dao.TieZiDao;
import com.banma.BF.entity.BanKuai;
import com.banma.BF.entity.TieZi;
import com.banma.BF.entity.User;

/**
 * Servlet implementation class UpdateTieziServlet
 */
@WebServlet({"/UpdateTieziServlet","/updateTiezi"})
public class UpdateTieziServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TieZiDao tieZiDao = new TieZiDao();
	private BanKuaiDao banKuaiDao = new BanKuaiDao();
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String tidstr = request.getParameter("tid");
		if(tidstr==null||tidstr.trim().isEmpty()) {
			//错误提示
			request.setAttribute("msg", "没有找到您想要的帖子信息<a href=\"index\">>>>>去首页</a>");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			return;
		}
		int tid = Integer.valueOf(tidstr);
		TieZi tieZi = tieZiDao.getTieziByTid(tid);
		
		if(tieZi==null) {
			//错误提示
			request.setAttribute("msg", "没有找到您想要的帖子信息<a href=\"index\">>>>>去首页</a>");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			return;
		}
		
		//判断是否为登陆状态
		User loginUser = (User) request.getSession().getAttribute("user");
		if(loginUser==null) {
			//错误提示
			request.setAttribute("msg", "请先登录<a href=\"login\">>>>>去登陆</a>");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			return;
		}
		
		//判断帖子发帖人是否和当前登陆用户一样
		if(tieZi.getUid()!=loginUser.getUid()) {
			//错误提示
			request.setAttribute("msg", "您没有修改权限<a href=\"tiezi?tid="+tieZi.getTid()+"\">>>>>去帖子详情页</a>");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			return;
		}
		
		
		//帖子所属板块信息
		BanKuai bk = banKuaiDao.getBanKuaiByBid(tieZi.getBid());
		 
		request.setAttribute("bk", bk);
		request.setAttribute("tiezi", tieZi);
		request.getRequestDispatcher("WEB-INF/updateTiezi.jsp").forward(request, response);
	}

	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String tidstr = request.getParameter("tid");
		if(tidstr==null||tidstr.trim().isEmpty()) {
			//错误提示
			request.getRequestDispatcher("WEB-INF/listError.jsp").forward(request, response);
			return;
		}
		int tid = Integer.valueOf(tidstr);
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		if(title==null||title.trim().isEmpty()||content==null||content.trim().isEmpty()) {
			//错误提示
			request.setAttribute("msg", "标题或内容不能为空<a href=\"updateTiezi?tid="+tid+"\" >>>>>去修改</a>");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			return;
		}
		
		User loginUser =  (User) request.getSession().getAttribute("user");
		if(loginUser==null) {
			//错误提示
			request.setAttribute("msg", "发帖之前您需要先登录<a href=\"login\">>>>>去登陆</a>");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
		
		
		TieZi tieZi = tieZiDao.getTieziByTid(tid);
		if(tieZi==null) {
			//错误提示
			request.setAttribute("msg", "没有找到您想要的帖子信息<a href=\"index\">>>>>去首页</a>");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			return;
		}
		
		//判断帖子发帖人是否和当前登陆用户一样
		if(tieZi.getUid()!=loginUser.getUid()) {
			//错误提示
			request.setAttribute("msg", "您没有修改权限<a href=\"tiezi?tid="+tieZi.getTid()+"\">>>>>去帖子详情页</a>");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			return;
		}
		
		
		
		/**
		 * 把表单中的数据更新到tieZi对象中
		 */
		tieZi.setContent(content);
		tieZi.setTitle(title);


				
		if(tieZiDao.updateTiezi(tieZi)) {//如果修改帖子成功
			response.sendRedirect("tiezi?tid="+tid);
		} else {
			//错误提示
			request.setAttribute("msg", "修改失败，请重试<a href=\"tiezi?tid="+tieZi.getTid()+"\">>>>>去帖子详情页</a>");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			return;
		}
	}

}
