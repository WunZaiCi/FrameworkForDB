package com.banma.BF.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banma.BF.dao.UserDao;
import com.banma.BF.entity.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet({"/LoginServlet","/login"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDao userDao = new UserDao();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 登录处理
		 */
		request.setCharacterEncoding("utf-8");
		String username =  request.getParameter("uName");
		String password =  request.getParameter("uPass");
		User user =  userDao.getUserByUserNameAndPW(username, password);
		if(user==null) {//用户对象如果为空，就说明账号密码不存在于数据库的表中
			request.setAttribute("msg", "账号密码错误");
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
			return;
		}
		
		//找到对应的用户信息给予登录操作
		request.getSession().setAttribute("user", user);
		
		response.sendRedirect("index");
	}

}
