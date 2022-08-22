package com.sist.model;
import controller.Controller;
import controller.RequestMapping;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.dao.*;
import com.sist.vo.*;

@Controller //얘는 Model입니다~ 라는 것을 DispatcherServlet에 알려 주려고.
public class SeoulModel {
	@RequestMapping("seoul/location.do")
	public String seoul_location(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		if(page==null)
			page="1";
		int curPage = Integer.parseInt(page);
		Map map = new HashMap();
		int rowSize = 16;
		int start = rowSize*curPage-(rowSize-1); //2페이지의 경우 16*2-(16-1) = 17부터
		int end = rowSize*curPage; //16*2 = 32까지.

		map.put("start", start); //mapper에서 지정한 이름과 map의 key명이 똑같아야 한다.
		map.put("end", end);
		map.put("table_name", "seoul_location");
		
		List<SeoulLNSVO> list = SeoulDAO.seoulLNSListData(map);
		int totalPage = SeoulDAO.seoulLNSTotalPage(map);
		
		//페이지네이션 영역 변수 설정
		final int BLOCK = 5;
		int startPage = ((curPage-1)/BLOCK*BLOCK)+1; //1~5까지 0*BLOCK+1로 처리됨
		int endPage = ((curPage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalPage) {
			endPage = totalPage;
		}
		
		request.setAttribute("curPage", curPage);
		request.setAttribute("startPage",startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("list", list);
		request.setAttribute("main_jsp", "../seoul/location.jsp");
		return "../main/main.jsp"; //main.jsp에다가 location.jsp를 첨부해서 보내줄 것임 -> 그래야 header, footer 유지
	}
	
	@RequestMapping("seoul/nature.do")
	public String seoul_nature(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		if(page==null)
			page="1";
		int curPage = Integer.parseInt(page);
		Map map = new HashMap();
		int rowSize = 16;
		int start = rowSize*curPage-(rowSize-1); //2페이지의 경우 16*2-(16-1) = 17부터
		int end = rowSize*curPage; //16*2 = 32까지.
		
		map.put("start", start); //mapper에서 지정한 이름과 map의 key명이 똑같아야 한다.
		map.put("end", end);
		map.put("table_name", "seoul_nature");
		
		List<SeoulLNSVO> list = SeoulDAO.seoulLNSListData(map);
		int totalPage = SeoulDAO.seoulLNSTotalPage(map);
		
		//페이지네이션 영역 변수 설정
		final int BLOCK = 5;
		int startPage = ((curPage-1)/BLOCK*BLOCK)+1; //1~5까지 0*BLOCK+1로 처리됨
		int endPage = ((curPage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalPage) {
			endPage = totalPage;
		}
		
		request.setAttribute("curPage", curPage);
		request.setAttribute("startPage",startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("list", list);
		request.setAttribute("main_jsp", "../seoul/nature.jsp");
		return "../main/main.jsp"; //main.jsp에다가 location.jsp를 첨부해서 보내줄 것임 -> 그래야 header, footer 유지
	}
	
	@RequestMapping("seoul/shop.do")
	public String seoul_shop(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		if(page==null)
			page="1";
		int curPage = Integer.parseInt(page);
		Map map = new HashMap();
		int rowSize = 16;
		int start = rowSize*curPage-(rowSize-1); //2페이지의 경우 16*2-(16-1) = 17부터
		int end = rowSize*curPage; //16*2 = 32까지.
		
		map.put("start", start); //mapper에서 지정한 이름과 map의 key명이 똑같아야 한다.
		map.put("end", end);
		map.put("table_name", "seoul_shop");
		
		List<SeoulLNSVO> list = SeoulDAO.seoulLNSListData(map);
		int totalPage = SeoulDAO.seoulLNSTotalPage(map);
		
		//페이지네이션 영역 변수 설정
		final int BLOCK = 5;
		int startPage = ((curPage-1)/BLOCK*BLOCK)+1; //1~5까지 0*BLOCK+1로 처리됨
		int endPage = ((curPage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalPage) {
			endPage = totalPage;
		}
		
		request.setAttribute("curPage", curPage);
		request.setAttribute("startPage",startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("list", list);
		request.setAttribute("main_jsp", "../seoul/shop.jsp");
		return "../main/main.jsp"; //main.jsp에다가 location.jsp를 첨부해서 보내줄 것임 -> 그래야 header, footer 유지
	}
	
	@RequestMapping("seoul/hotel.do")
	public String seoul_hotel(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		if(page==null)
			page="1";
		int curPage = Integer.parseInt(page);
		Map map = new HashMap();
		int rowSize = 16;
		int start = rowSize*curPage-(rowSize-1); //2페이지의 경우 16*2-(16-1) = 17부터
		int end = rowSize*curPage; //16*2 = 32까지.
		
		map.put("start", start); //mapper에서 지정한 이름과 map의 key명이 똑같아야 한다.
		map.put("end", end);
		map.put("table_name", "seoul_hotel");
		
		List<SeoulLNSVO> list = SeoulDAO.seoulLNSListData(map);
		int totalPage = SeoulDAO.seoulLNSTotalPage(map);
		
		//페이지네이션 영역 변수 설정
		final int BLOCK = 5;
		int startPage = ((curPage-1)/BLOCK*BLOCK)+1; //1~5까지 0*BLOCK+1로 처리됨
		int endPage = ((curPage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalPage) {
			endPage = totalPage;
		}
		
		request.setAttribute("curPage", curPage);
		request.setAttribute("startPage",startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("list", list);
		request.setAttribute("main_jsp", "../seoul/hotel.jsp");
		return "../main/main.jsp"; //main.jsp에다가 location.jsp를 첨부해서 보내줄 것임 -> 그래야 header, footer 유지
	}
	
	@RequestMapping("seoul/guest.do")
	public String seoul_guest(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		if(page==null)
			page="1";
		int curPage = Integer.parseInt(page);
		Map map = new HashMap();
		int rowSize = 16;
		int start = rowSize*curPage-(rowSize-1); //2페이지의 경우 16*2-(16-1) = 17부터
		int end = rowSize*curPage; //16*2 = 32까지.
		
		map.put("start", start); //mapper에서 지정한 이름과 map의 key명이 똑같아야 한다.
		map.put("end", end);
		map.put("table_name", "seoul_guest");
		
		List<SeoulLNSVO> list = SeoulDAO.seoulLNSListData(map);
		int totalPage = SeoulDAO.seoulLNSTotalPage(map);
		
		//페이지네이션 영역 변수 설정
		final int BLOCK = 5;
		int startPage = ((curPage-1)/BLOCK*BLOCK)+1; //1~5까지 0*BLOCK+1로 처리됨
		int endPage = ((curPage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalPage) {
			endPage = totalPage;
		}
		
		request.setAttribute("curPage", curPage);
		request.setAttribute("startPage",startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("list", list);
		request.setAttribute("main_jsp", "../seoul/guest.jsp");
		return "../main/main.jsp"; //main.jsp에다가 location.jsp를 첨부해서 보내줄 것임 -> 그래야 header, footer 유지
	}
	
	@RequestMapping("seoul/lns_detail.do")
	public String seoul_detail(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		String cno = request.getParameter("cno");
		String table_name = "seoul_";
		
		if(cno.equals("1")) {
			table_name += "location";
		}else if(cno.equals("2")) {
			table_name += "nature";
		}else if(cno.equals("3")) {
			table_name += "shop";
		}
		
		Map map = new HashMap();
		map.put("table_name", table_name);
		map.put("no", no);
		
		SeoulLNSVO vo = SeoulDAO.seoulDetailData(map);
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../seoul/lns_detail.jsp");
		return "../main/main.jsp";
	}
}
