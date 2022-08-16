package doodoo.model;

import controller.Controller;
import controller.RequestMapping;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import doodoo.dao.*;
@Controller
public class ReserveModel {
	
	@RequestMapping("reserve/reserve.do")
	public String reserve_main(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		if(type==null)
			type = "한식";
		List<FoodVO> list = ReserveDAO.foodListData(type);
		request.setAttribute("list", list);
		return "../reserve/reserve.jsp";
	}
	
	@RequestMapping("reserve/select.do")
	public String food_select(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		if(type==null)
			type = "한식";
		List<FoodVO> list = ReserveDAO.foodListData(type);
		request.setAttribute("list", list);
		return "../reserve/select.jsp";
	}
}
