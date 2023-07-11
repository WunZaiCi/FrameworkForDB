package com.banma.BF.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.banma.BF.dao.BanKuaiDao;
import com.banma.BF.dao.TieZiDao;
import com.banma.BF.dao.UserDao;
import com.banma.BF.entity.BanKuai;
import com.banma.BF.entity.TieZi;
import com.banma.BF.entity.User;

/**
 * Servlet implementation class FatieServlet
 */
@WebServlet({"/FatieServlet","/fatie"})
public class FatieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BanKuaiDao banKuaiDao = new BanKuaiDao();
	TieZiDao tieZiDao = new TieZiDao();
	UserDao userDao = new UserDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		String bidstr = request.getParameter("bid");
		if(bidstr==null||bidstr.trim().isEmpty()) {
			//错误提示
			request.getRequestDispatcher("WEB-INF/listError.jsp").forward(request, response);
			return;
		}
		int bid = Integer.valueOf(bidstr);
		   
		User user =  (User) request.getSession().getAttribute("user");
		if(user==null) {
			//错误提示
			request.setAttribute("msg", "发帖之前您需要先登录<a href=\"login\">>>>>去登陆</a>");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
		
		
		BanKuai banKuai = banKuaiDao.getBanKuaiByBid(bid);	
		request.setAttribute("bk", banKuai);
		
		request.getRequestDispatcher("WEB-INF/fatie.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String bidstr = request.getParameter("bid");
		if(bidstr==null||bidstr.trim().isEmpty()) {
			//错误提示
			request.getRequestDispatcher("WEB-INF/listError.jsp").forward(request, response);
			return;
		}
		int bid = Integer.valueOf(bidstr);
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		if(title==null||title.trim().isEmpty()||content==null||content.trim().isEmpty()) {
			//错误提示
			request.setAttribute("msg", "标题或内容不能为空<a href=\"fatie?bid="+bid+"\" >>>>>去发帖</a>");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			return;
		}
		
		User user =  (User) request.getSession().getAttribute("user");
		if(user==null) {
			//错误提示
			request.setAttribute("msg", "发帖之前您需要先登录<a href=\"login\">>>>>去登陆</a>");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
		
		/**
		 * 把表单中的数据 封装到Tiezi对象中
		 */
		TieZi tieZi =new TieZi();
		tieZi.setBid(bid);
		tieZi.setContent(content);
		tieZi.setTitle(title);
		tieZi.setUid(user.getUid());
		
		long tid = tieZiDao.addTiezi(tieZi);
				
		if(tid>0) {
			response.sendRedirect("tiezi?tid="+tid);
		} else {
			request.setAttribute("msg", "发帖失败，请重新尝试<a href=\"fatie\">>>>>去发帖</a>");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
	}
}
