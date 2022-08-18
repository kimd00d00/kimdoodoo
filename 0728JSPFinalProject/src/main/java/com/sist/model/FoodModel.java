package com.sist.model;

import controller.Controller;
import controller.RequestMapping;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.dao.*;
import com.sist.vo.*;

@Controller
public class FoodModel {
	@RequestMapping("food/food_list.do")//카테고리별 맛집 리스트를 가져와라
	public String food_list(HttpServletRequest request, HttpServletResponse response) {
		//카테고리 번호를 받는다.
		String cno = request.getParameter("cno");
		//cno를 DAO에 보내서 데이터를 받아 온다.
		List<FoodVO> list = FoodDAO.foodListData(Integer.parseInt(cno));
		FoodCategoryVO vo = FoodDAO.foodCategoryInfoData(Integer.parseInt(cno));
		request.setAttribute("list", list);
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../food/food_list.jsp");//보여지는건 얘고
		return "../main/main.jsp"; //실행은 얘가 하는거다
	}
	
	//쿠키를 만들어보자
	@RequestMapping("food/food_detail_before.do")
	public String food_detail_before(HttpServletRequest request, HttpServletResponse response) {
		// 쿠키 전송 => 화면 출력이 아니다.
		String fno = request.getParameter("fno");
		Cookie cookie = new Cookie("food"+fno,fno);
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);//하루동안 저장
		response.addCookie(cookie);
		//상세보기로 이동
		return "redirect:../food/food_detail.do?fno="+fno; //request 초기화됨
	}
	
	@RequestMapping("food/food_detail.do")
	public String food_detail(HttpServletRequest request, HttpServletResponse response) {
		//사용자가 보내 준 값을 읽는다.
		String fno = request.getParameter("fno");
		//데이터베이스 연동한다.
		FoodVO vo = FoodDAO.foodDetailData(Integer.parseInt(fno));
		//address값 가공
		String address = vo.getAddress();
		String addr1 = address.substring(0,address.lastIndexOf("지"));
		String addr2 = address.substring(address.lastIndexOf("지")+2);
		request.setAttribute("addr1", addr1.trim());
		request.setAttribute("addr2", addr2.trim());
		//request에 담아서 넘겨 준다.
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp","../food/food_detail.jsp");
		
		//찜하기와 관련된 영역
		JjimVO jvo = new JjimVO();
		jvo.setFno(Integer.parseInt(fno));
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		jvo.setId(id);
		int jcount = FoodDAO.foodJjimCount(jvo);
		int lcount = 0;
		request.setAttribute("fno", Integer.parseInt(fno));
		request.setAttribute("jcount", jcount);
		request.setAttribute("likecount", 0);
		
		//관련 레시피 전송
		String type=vo.getType();
		type = type.replace("/", "|").replace(" ", "");
		List<RecipeVO> rList = FoodDAO.foodRecipeMakeData(type);
		request.setAttribute("rList", rList);
		
		//어떤 JSP로 보낼지 설정한다
		return "../main/main.jsp";
	}
	
	@RequestMapping("food/jjim.do")
	public String food_jjim(HttpServletRequest request, HttpServletResponse response) {
		String fno = request.getParameter("fno");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		JjimVO vo = new JjimVO();
		vo.setFno(Integer.parseInt(fno));
		vo.setId(id);
		FoodDAO.foodJjimInsert(vo);
		return "redirect:../food/food_detail.do?fno="+fno;
	}
	
	@RequestMapping("food/food_find.do")
	public String food_find(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex) {}
		String page = request.getParameter("page");
		if(page==null)
			page = "1";
		String addr = request.getParameter("addr");
		if(addr==null)
			addr="강남";
		int curPage = Integer.parseInt(page);
		int rowSize = 12;
		int start = (rowSize*curPage)-(rowSize-1);
		int end = rowSize*curPage;
		
		Map map = new HashMap();
		map.put("address", addr);
		map.put("start", start);
		map.put("end", end);
		
		List<FoodVO> list = FoodDAO.foodLocationFindData(map);
		int totalPage = FoodDAO.foodLocationFindTotalPage(addr);
		System.out.println(totalPage);
		request.setAttribute("curPage", curPage);
		request.setAttribute("list", list);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("addr", addr);
		request.setAttribute("main_jsp", "../food/food_find.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("food/jjim_list.do")
	public String food_jjim_list(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		List<Integer> list = FoodDAO.foodJjimGetFno(id);
		List<FoodVO> fList = new ArrayList<FoodVO>();
		for(int fno:list) {
			FoodVO vo = FoodDAO.foodJjimListData(fno);
			fList.add(vo);
		}
		request.setAttribute("fList", fList);
		request.setAttribute("mypage_jsp", "../mypage/jjim_list.jsp");
		request.setAttribute("main_jsp", "../mypage/mypage.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("food/jjim_cancel.do")
	public String food_jjim_cancel(HttpServletRequest request, HttpServletResponse response) {
		String fno = request.getParameter("fno");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		JjimVO vo = new JjimVO();
		vo.setId(id);
		vo.setFno(Integer.parseInt(fno));
		//DB연동!
		FoodDAO.foodJjimDelete(vo);
		return "redirect:../food/jjim_list.do";
	}
}
