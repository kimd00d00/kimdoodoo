package com.sist.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;

@Controller
public class MainModel {
	@RequestMapping("main/main.do")
	public String main_page(HttpServletRequest request, HttpServletResponse response) {
		List<FoodCategoryVO> list = FoodDAO.foodCategoryData();
		request.setAttribute("list", list);
		request.setAttribute("main_jsp", "../main/home.jsp");
		return "../main/main.jsp";
	}
}
