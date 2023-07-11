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
import com.banma.BF.vo.ListPageVO;
import com.banma.BF.vo.TieZiVO;

/**
 * Servlet implementation class ListServlet
 */
@WebServlet({"/ListServlet","/list"})
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TieZiDao tieZiDao = new TieZiDao();   
	private UserDao userDao = new UserDao();   
	private HuifuDao huifuDao = new HuifuDao();   
	private BanKuaiDao banKuaiDao = new BanKuaiDao();   
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		
		String bidstr = request.getParameter("bid");
		if(bidstr==null||bidstr.trim().isEmpty()) {
			request.getRequestDispatcher("WEB-INF/listError.jsp").forward(request, response);
			return;
		}
		int bid = Integer.valueOf(bidstr);
		ListPageVO vo = new ListPageVO();
		List<TieZiVO> list = new ArrayList<TieZiVO>();
		
		
		String pagestr = request.getParameter("page");
		int page = 1;
		if(pagestr!=null) {
			page=Integer.valueOf(pagestr);
			if(page==0) {
				page=1;
			}
		}
		
		//根据板块bid得到板块下所有的帖子
		List<TieZi>  tieziList =  tieZiDao.getTieZiByBid(bid,page);
		
		if(tieziList!=null) {
			for(TieZi tieZi :tieziList ) {
				TieZiVO tvo= new TieZiVO();
				tvo.setTid(tieZi.getTid());
				tvo.setTitle(tieZi.getTitle());
				tvo.setUpdateTime(tieZi.getUpdateTime());
				
				//查询该帖子的发帖人信息
				User user = userDao.getUser(tieZi.getUid());
				if(user!=null) {
					tvo.setUsername(user.getUsername());
				}else {
					tvo.setUsername("未知用户");
				}
				
				//查询该帖子的回复数量
				List<HuiFu> hList = huifuDao.getHuifuByTid(tieZi.getTid());
				int huiCount = hList == null?0: hList.size();
				tvo.setHuiFuCount(huiCount);					
			
				list.add(tvo);
			}
		}
		 
		vo.setBanKuai(banKuaiDao.getBanKuaiByBid(bid));				
		vo.setTiezi(list);
		request.setAttribute("vo", vo);
		
		int prePageIndex = page- 1;
		int nextPageIndex = page+ 1;
		if(prePageIndex<1) {
			prePageIndex = 1;
		}
		
		
		request.setAttribute("prePageIndex", prePageIndex);//上一页的页码
		request.setAttribute("nextPageIndex", nextPageIndex);//下一页的页码
		request.getRequestDispatcher("WEB-INF/list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
