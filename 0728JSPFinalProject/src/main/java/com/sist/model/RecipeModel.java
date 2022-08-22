package com.sist.model;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.RecipeDAO;
import com.sist.vo.*;

import controller.Controller;
import controller.RequestMapping;

@Controller
public class RecipeModel {
	@RequestMapping("recipe/recipe_list.do")
	public String recipe_list(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		if(page==null)
			page="1";
		int curPage = Integer.parseInt(page);
		
		int rowSize = 9;
		int start = (rowSize*curPage)-(rowSize-1);
		int end = rowSize*curPage;
		
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<RecipeVO> list = RecipeDAO.recipeListData(map);
		
		for(RecipeVO vo:list) {
			String title=vo.getTitle();
			if(title.length()>10)
				title= title.substring(0,10)+"...";
			vo.setTitle(title);
		}
		
		int totalPage = RecipeDAO.recipeTotalPage();
		
		//블록별...
		final int BLOCK = 10;
		int startPage = ((curPage-1)/BLOCK*BLOCK)+1;
		int endPage = ((curPage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalPage)
			endPage=totalPage;
		
		request.setAttribute("curPage", curPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("list", list);
		request.setAttribute("main_jsp", "../recipe/recipe_list.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("recipe/chef_list.do")
	public String chef_list(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		if(page==null)
			page="1";
		int curPage = Integer.parseInt(page);
		
		int rowSize = 20;
		int start = (rowSize*curPage)-(rowSize-1);
		int end = rowSize*curPage;
		
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<ChefVO> list = RecipeDAO.chefListData(map);
		int totalPage = RecipeDAO.chefTotalPage();
		
		//블록별...
		final int BLOCK = 10;
		int startPage = ((curPage-1)/BLOCK*BLOCK)+1;
		int endPage = ((curPage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalPage)
			endPage=totalPage;
		
		request.setAttribute("curPage", curPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("list", list);
		request.setAttribute("main_jsp", "../recipe/chef_list.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("recipe/chef_make.do")
	public String chef_make(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("main_jsp", "../recipe/chef_make.jsp");
		return "../main/main.jsp";
	}
}
