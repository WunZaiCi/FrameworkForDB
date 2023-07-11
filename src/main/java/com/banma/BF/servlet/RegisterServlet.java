package com.banma.BF.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banma.BF.dao.UserDao;
import com.banma.BF.entity.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet({"/RegisterServlet","/register"})
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UserDao userDao =new UserDao();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 注册处理
		 * 成功以后  重定向去登录页
		 */
		request.setCharacterEncoding("utf-8");
		String username =  request.getParameter("uName");
		String password =  request.getParameter("uPass");
		String password1 =  request.getParameter("uPass1");
		String sex =  request.getParameter("gender");
		String head =  request.getParameter("head");
		
		if(password==null||password.trim().isEmpty()||
				password1==null||password1.trim().isEmpty()||
				username==null||username.trim().isEmpty()||
				sex==null||sex.trim().isEmpty()) {
			request.setAttribute("msg", "参数错误,请输入完成的数据");
			request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
			return;
		}
		
		if(password1==null||!password.equals(password1)) {
			request.setAttribute("msg", "两次密码不一致，请重新输入");
			request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
			return;
		}
		
		
		
		User user = new User();
		user.setCreateTime(new Date());
		user.setSex(sex);
		user.setPassword(password);
		user.setUsername(username);
		user.setHeadImage(head);
		
		if(userDao.addUser(user)) {
			request.getRequestDispatcher("WEB-INF/registerSuccess.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
		}
		
	}

}
