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
import com.banma.BF.dao.TieZiDao;
import com.banma.BF.dao.UserDao;
import com.banma.BF.entity.BanKuai;
import com.banma.BF.entity.TieZi;
import com.banma.BF.entity.User;
import com.banma.BF.vo.BanKuaiVO;
import com.banma.BF.vo.IndexVO;
import com.banma.BF.vo.TieZiVO;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet({ "/IndexServlet", "/index" })
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BanKuaiDao banKuaiDao = new BanKuaiDao();
	private TieZiDao tieZiDao = new TieZiDao();
	private UserDao userDao = new UserDao();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IndexVO indexVO = new IndexVO();
		List<BanKuaiVO> list = new ArrayList<BanKuaiVO>();
		
		List<BanKuai> rootBankaiList = banKuaiDao.getBanKuaiByPid(0);
		if(rootBankaiList!=null) {
			for(BanKuai banKuai : rootBankaiList) {
				//将板块实体类的信息转换到板块页面实体类中
				BanKuaiVO banKuaivo = new BanKuaiVO();
				banKuaivo.setBname(banKuai.getBname());
				
				//查询该板块的所有子版块
				List<BanKuai> cBankaiList = banKuaiDao.getBanKuaiByPid(banKuai.getBid());
				List<BanKuaiVO> clist = new ArrayList<BanKuaiVO>();
				if(rootBankaiList!=null) {
					for(BanKuai cb : cBankaiList) {
						int cbid = cb.getBid();
						BanKuaiVO cbvo = new BanKuaiVO();
						cbvo.setBname(cb.getBname());
						cbvo.setBid(cbid);
						
						//查询该板块的帖子数量
						List<TieZi> tieziList= tieZiDao.getTieZiByBid(cbid);
						int tieCount;
						if(tieziList== null) {
							tieCount=0;
						}else {
							tieCount=tieziList.size();
						}
						
						cbvo.setTieCount(tieCount);
						 
						if(tieCount>0) {
							//查询该板块最后更改的帖子  							
							TieZi tiezi =  tieZiDao.getLastUpdateTieziByBid(cbid);
							
							if(tiezi!=null) {
								TieZiVO tvo=  new TieZiVO();
								tvo.setTid(tiezi.getTid());
								tvo.setTitle(tiezi.getTitle());
								User user = userDao.getUser(tiezi.getUid());
								if(user!=null) {
									tvo.setUsername(user.getUsername());
								}else {
									tvo.setUsername("未知用户");
								}
								tvo.setUpdateTime(tiezi.getUpdateTime());
								cbvo.setLastTieZi(tvo);
							}
						}
						
						
						clist.add(cbvo);
					}
				}
				banKuaivo.setcList(clist);
				list.add(banKuaivo);
				
			}
		}
		indexVO.setBanKuais(list);
		request.setAttribute("indexVO", indexVO);
		request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
	}



}
