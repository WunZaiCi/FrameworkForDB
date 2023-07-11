package com.banma.BF.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banma.BF.dao.BanKuaiDao;
import com.banma.BF.dao.HuifuDao;
import com.banma.BF.dao.TieZiDao;
import com.banma.BF.dao.UserDao;
import com.banma.BF.entity.HuiFu;
import com.banma.BF.entity.TieZi;
import com.banma.BF.entity.User;
import com.banma.BF.vo.HuiFuVO;
import com.banma.BF.vo.TieZiPageVO;

/**
 * Servlet implementation class TieziServlet
 */
@WebServlet({"/TieziServlet","/tiezi"})
public class TieziServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BanKuaiDao banKuaiDao = new BanKuaiDao();
	private TieZiDao tieZiDao = new TieZiDao();
	private UserDao userDao = new UserDao();
	private HuifuDao huifuDao = new HuifuDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String tidstr = request.getParameter("tid");
		if(tidstr==null||tidstr.trim().isEmpty()) {
			//错误提示
			request.setAttribute("msg", "没有找到您想要的帖子信息<a href=\"index\">>>>>去首页</a>");
			request.getRequestDispatcher("WEB-INF/listError.jsp").forward(request, response);
			return;
		}
		int tid = Integer.valueOf(tidstr);
		
		String pagestr = request.getParameter("page");
		int page = 1;
		if(pagestr!=null) {
			page=Integer.valueOf(pagestr);
			if(page==0) {
				page=1;
			}
		}	
		
		TieZiPageVO vo = new TieZiPageVO();
		
		 //帖子信息
		TieZi tieZi= tieZiDao.getTieziByTid(tid);
		vo.setTiezi(tieZi); 
		 
		//帖子所属板块信息
		 vo.setBanKuai(banKuaiDao.getBanKuaiByBid(tieZi.getBid()));
		
		 //发帖人信息
		 User user =  userDao.getUser(tieZi.getUid());
		 vo.setUser(user);
		 
		
		 
		//初始化模拟数据 回复消息信息
		 List<HuiFu> hList = huifuDao.getHuifuByTid(tid,page);
		
		 if(hList!=null) {
			 List<HuiFuVO> hVos = new ArrayList<>();
			 for(HuiFu huiFu:hList) {
				 HuiFuVO huiFuVO = new HuiFuVO();
				 huiFuVO.setHuifu(huiFu);
				 //根据回复信息中的uid获得user  此user指该回复的用户！！！
				 huiFuVO.setUser(userDao.getUser(huiFu.getUid()));
				 hVos.add(huiFuVO);
			 }
			 vo.setList(hVos);
		 }
		
		
		request.setAttribute("vo", vo);
		
		int prePageIndex = page- 1;
		int nextPageIndex = page+ 1;
		if(prePageIndex<1) {
			prePageIndex = 1;
		}
		request.setAttribute("prePageIndex", prePageIndex);//上一页的页码
		request.setAttribute("nextPageIndex", nextPageIndex);//下一页的页码
		request.getRequestDispatcher("WEB-INF/tiezi.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
