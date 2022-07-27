package model;

import controller.Controller;
import controller.RequestMapping;
import dao.*;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FoodModel {
	@RequestMapping("food/category.do")
	public String food_category(HttpServletRequest request) {
		String no = request.getParameter("no");
		FoodDAO dao = new FoodDAO();
		List<CategoryVO> list = dao.categoryListData(Integer.parseInt(no));
		request.setAttribute("list", list);
		System.out.println("food_category() call~");
//		request.setAttribute("msg", "카테고리 출력합니다.");
		return "../food/category.jsp";
	}
	@RequestMapping("food/food_list.do")
	public String food_list(HttpServletRequest request) {
		System.out.println("food_list() call~");
//		request.setAttribute("msg", "카테고리별 맛집 출력합니다.");
		return "../food/food_list.jsp";
	}
	@RequestMapping("food/detail.do")
	public String food_detail(HttpServletRequest request) {
		System.out.println("food_detail() call~");
//		request.setAttribute("msg", "맛집 상세 출력합니다.");
		return "../food/detail.jsp";
	}
}
