package com.banma.BF.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banma.BF.dao.BanKuaiDao;
import com.banma.BF.dao.HuifuDao;
import com.banma.BF.dao.TieZiDao;
import com.banma.BF.entity.BanKuai;
import com.banma.BF.entity.HuiFu;
import com.banma.BF.entity.TieZi;
import com.banma.BF.entity.User;

/**
 * Servlet implementation class UpdateHuifuServlet
 */
@WebServlet({"/UpdateHuifuServlet","/updateHuifu"})
public class UpdateHuifuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HuifuDao huifuDao = new HuifuDao();
	private TieZiDao tieZiDao = new TieZiDao();
	private BanKuaiDao banKuaiDao = new BanKuaiDao();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String hidstr = request.getParameter("hid");
		if(hidstr==null||hidstr.trim().isEmpty()) {
			//错误提示
			request.setAttribute("msg", "没有对应的回复信息<a href=\"index\">>>>>去首页</a>");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			return;
		}
		int hid = Integer.valueOf(hidstr);
		
		String tidstr = request.getParameter("tid");
		if(tidstr==null||tidstr.trim().isEmpty()) {
			//错误提示
			request.setAttribute("msg", "没有对应的帖子信息<a href=\"index\">>>>>去首页</a>");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			return;
		}
		int tid = Integer.valueOf(tidstr);
		
		
		
		HuiFu huiFu = huifuDao.getHuifuByHid(hid);
		
		
		if(huiFu==null) {
			//错误提示
			request.setAttribute("msg", "没有对应的回复信息<a href=\"tiezi?tid="+tid+"\">>>>>去帖子详情页</a>");
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
		if(huiFu.getUid()!=loginUser.getUid()) {
			//错误提示
			request.setAttribute("msg", "您没有修改权限<a href=\"tiezi?tid="+huiFu.getTid()+"\">>>>>去帖子详情页</a>");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			return;
		}
		
		TieZi tieZi = tieZiDao.getTieziByTid(huiFu.getTid());
		//帖子所属板块信息		
		BanKuai bk = banKuaiDao.getBanKuaiByBid(tieZi.getBid());		 
		request.setAttribute("bk", bk);
		request.setAttribute("huifu", huiFu);
		request.setAttribute("tiezi", tieZi);
		request.getRequestDispatcher("WEB-INF/updateHuifu.jsp").forward(request, response);
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String hidstr = request.getParameter("hid");
		if(hidstr==null||hidstr.trim().isEmpty()) {
			//错误提示
			request.getRequestDispatcher("WEB-INF/listError.jsp").forward(request, response);
			return;
		}
		int hid = Integer.valueOf(hidstr);
		String content = request.getParameter("content");
		HuiFu huiFu = huifuDao.getHuifuByHid(hid);
		
		if(content==null||content.trim().isEmpty()) {
			//错误提示
			request.setAttribute("msg", "标题或内容不能为空<a href=\"updateHuifu?hid="+hid+"\" >>>>>去修改</a>");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			return;
		}
		
		User loginUser =  (User) request.getSession().getAttribute("user");
		if(loginUser==null) {
			//错误提示
			request.setAttribute("msg", "发帖之前您需要先登录<a href=\"login\">>>>>去登陆</a>");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
		

		if(huiFu==null) {
			//错误提示
			request.setAttribute("msg", "没有对应的回复信息<a href=\"index\">>>>>去首页</a>");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			return;
		}
		
		//判断帖子发帖人是否和当前登陆用户一样
		if(huiFu.getUid()!=loginUser.getUid()) {
			//错误提示
			request.setAttribute("msg", "您没有修改权限<a href=\"tiezi?tid="+huiFu.getTid()+"\">>>>>去帖子详情页</a>");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			return;
		}
		
		
		
		/**
		 * 把表单中的数据更新到huifu对象中
		 */
		huiFu.setContent(content);


				
		if(huifuDao.updateHuifu(huiFu)) {//如果修改帖子成功
			response.sendRedirect("tiezi?tid="+huiFu.getTid());
		} else {
			//错误提示
			request.setAttribute("msg", "修改失败，请重试<a href=\"huifu?tid="+huiFu.getTid()+"\">>>>>去帖子详情页</a>");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			return;
		}
	}

}
