package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import com.sist.dao.GoodsDAO;
import com.sist.vo.GoodsVO;

import controller.Controller;
import controller.RequestMapping;

@Controller
public class GoodsModel {
	@RequestMapping("goods_main/goods_main.do")
	public String goods_main(HttpServletRequest request, HttpServletResponse response) {
		//베스트 상품
		List<GoodsVO> bList = GoodsDAO.goodsHomeData("goods_best");
		//스페셜 상품
		List<GoodsVO> sList = GoodsDAO.goodsHomeData("goods_special");
		//신상품
		List<GoodsVO> nList = GoodsDAO.goodsHomeData("goods_new");
		
		request.setAttribute("bList", bList);
		request.setAttribute("sList", sList);
		request.setAttribute("nList", nList);
		request.setAttribute("goods_jsp", "../goods_main/goods_home.jsp");
		return "../goods_main/goods_main.jsp";
	}
	
	@RequestMapping("goods_main/goods_all.do")
	public String goods_all(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		if(page==null)
			page="1";
		int curPage = Integer.parseInt(page);
		//DAO 연결
		Map map = new HashMap();
		int rowSize = 9;
		int start = (rowSize*curPage)-(rowSize-1);
		int end = rowSize*curPage;
		
		map.put("start", start);
		map.put("end", end);
		map.put("table_name", "goods_all");
		List<GoodsVO> list = GoodsDAO.goodsListData(map);
		int totalPage = GoodsDAO.goodsTotalPage("goods_all");
		
		request.setAttribute("curPage", curPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("list", list);
		request.setAttribute("goods_jsp", "../goods_main/goods_all.jsp");
		return "../goods_main/goods_main.jsp";
	}
	@RequestMapping("goods_main/goods_best.do")
	public String goods_best(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		if(page==null)
			page="1";
		int curPage = Integer.parseInt(page);
		//DAO 연결
		Map map = new HashMap();
		int rowSize = 9;
		int start = (rowSize*curPage)-(rowSize-1);
		int end = rowSize*curPage;
		
		map.put("start", start);
		map.put("end", end);
		map.put("table_name", "goods_best");
		List<GoodsVO> list = GoodsDAO.goodsListData(map);
		int totalPage = GoodsDAO.goodsTotalPage("goods_best");
		
		request.setAttribute("curPage", curPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("list", list);
		request.setAttribute("goods_jsp", "../goods_main/goods_best.jsp");
		return "../goods_main/goods_main.jsp";
	}
	@RequestMapping("goods_main/goods_new.do")
	public String goods_new(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		if(page==null)
			page="1";
		int curPage = Integer.parseInt(page);
		//DAO 연결
		Map map = new HashMap();
		int rowSize = 9;
		int start = (rowSize*curPage)-(rowSize-1);
		int end = rowSize*curPage;
		
		map.put("start", start);
		map.put("end", end);
		map.put("table_name", "goods_new");
		List<GoodsVO> list = GoodsDAO.goodsListData(map);
		int totalPage = GoodsDAO.goodsTotalPage("goods_new");
		
		request.setAttribute("curPage", curPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("list", list);
		request.setAttribute("goods_jsp", "../goods_main/goods_new.jsp");
		return "../goods_main/goods_main.jsp";
	}
	@RequestMapping("goods_main/goods_special.do")
	public String goods_special(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		if(page==null)
			page="1";
		int curPage = Integer.parseInt(page);
		//DAO 연결
		Map map = new HashMap();
		int rowSize = 9;
		int start = (rowSize*curPage)-(rowSize-1);
		int end = rowSize*curPage;
		
		map.put("start", start);
		map.put("end", end);
		map.put("table_name", "goods_special");
		List<GoodsVO> list = GoodsDAO.goodsListData(map);
		int totalPage = GoodsDAO.goodsTotalPage("goods_special");
		
		request.setAttribute("curPage", curPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("list", list);
		request.setAttribute("goods_jsp", "../goods_main/goods_special.jsp");
		return "../goods_main/goods_main.jsp";
	}
	@RequestMapping("goods_main/goods_detail.do")
	public String goods_detail(HttpServletRequest request, HttpServletResponse response) {
		String strNo = request.getParameter("no");
		String strCno = request.getParameter("cno");
		int no = Integer.parseInt(strNo);
		int cno = Integer.parseInt(strCno);
		String table_name = "";
		if(cno==1)	table_name = "goods_all";
		else if(cno==2) table_name="goods_best";
		else if(cno==3) table_name="goods_special";
		else if(cno==4) table_name="goods_new";
		
		Map map = new HashMap();
		map.put("table_name", table_name);
		map.put("no",no);
		
		GoodsVO vo = GoodsDAO.goodsDetailData(map);
		
		request.setAttribute("vo", vo);
		request.setAttribute("goods_jsp", "../goods_main/goods_detail.jsp");
		return "../goods_main/goods_main.jsp";
	}
}
