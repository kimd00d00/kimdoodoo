package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;

@Controller
public class ReserveModel {
	@RequestMapping("reserve/reserve.do")
	public static String reserve(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("main_jsp","../reserve/reserve.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("reserve/food_list.do")
	public static String reserve_food_list(HttpServletRequest request, HttpServletResponse response) {
		String type=request.getParameter("type");
		if(type==null)
			type="한식";
		//데이터 읽기
		List<FoodVO> list = ReserveDAO.reserveFoodData(type);
		request.setAttribute("list", list);
		request.setAttribute("main_jsp","../reserve/reserve.jsp");
		return "../reserve/food_list.jsp";
	}
	@RequestMapping("reserve/reserve_date.do")//달력 출력
	public static String reserve_date(HttpServletRequest request, HttpServletResponse response) {
		String fno = request.getParameter("fno");
		String today = new SimpleDateFormat("yyyy-M-d").format(new Date());//오늘 날짜
		StringTokenizer st = new StringTokenizer(today,"-");
		String strYear = st.nextToken();
		String strMonth = st.nextToken();
		String strDay = st.nextToken();
		
		int year = Integer.parseInt(strYear);
		int month = Integer.parseInt(strMonth);
		int day = Integer.parseInt(strDay);
		Calendar cal = Calendar.getInstance();//달력 객체 생성
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DATE, 1);
		
		int week=cal.get(Calendar.DAY_OF_WEEK);//요일 가져옴
		int lastDay= cal.getActualMaximum(Calendar.DATE); //이 날짜가 있는 달의 마지막 날짜를 가져와라
		String[] strWeek = {"일","월","화","수","목","금","토"};
		
		//달력 출력
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("day", day);
		request.setAttribute("week", week-1);
		request.setAttribute("lastDay", lastDay);
		request.setAttribute("strWeek", strWeek);
		
		//DAO로 예약이 가능한 날 체크
		//1. 이미 예약된 날을 가져온다.
		String rdays = ReserveDAO.reserveGetDate(Integer.parseInt(fno));
		StringTokenizer st1 = new StringTokenizer(rdays,",");
		int[] days = new int[32]; //0부터 31까지 32개
		
		while(st1.hasMoreTokens()) {
			int d = Integer.parseInt(st1.nextToken());
			if(d>=day) //오늘이후날짜만 확인하면되지
				days[d] = 1; //0이면 예약없는날, 1이면 예약있는날
		}
		request.setAttribute("days", days);
		return "../reserve/reserve_date.jsp";
	}
	
	@RequestMapping("reserve/reserve_time.do")
	public static String reserve_time(HttpServletRequest request, HttpServletResponse response) {
		String day = request.getParameter("day");
		//DAO->시간대 읽기
		String times = ReserveDAO.reserveGetTime(Integer.parseInt(day));
		List<String> list = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(times,",");
		while(st.hasMoreTokens()) {
			int tno=Integer.parseInt(st.nextToken());
			String time = ReserveDAO.reserveRealTime(tno);
			list.add(time);
		}
		request.setAttribute("list", list);
		return "../reserve/reserve_time.jsp";
	}
	@RequestMapping("reserve/reserve_capa.do")
	public static String reserve_capa(HttpServletRequest request, HttpServletResponse response) {
		
		return "../reserve/reserve_capa.jsp";
	}
	
	//예약 INSERT 처리
	@RequestMapping("reserve/reserve_ok.do")
	public static String reserve_ok(HttpServletRequest request, HttpServletResponse response) {
		try {
			//한글 변환
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		//식당번호, 예약날짜, 예약시간, 인원수
		String fno = request.getParameter("fno");
		String rday = request.getParameter("rday");
		String rtime = request.getParameter("rtime");
		String capa = request.getParameter("capa");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ReserveVO vo = new ReserveVO();
		vo.setId(id);
		vo.setFno(Integer.parseInt(fno));
		vo.setRday(rday);
		vo.setRtime(rtime);
		vo.setCapa(capa);
		
		ReserveDAO.reserveInsert(vo);
		
		return "redirect:../mypage/mypage_reserve.do";
	}
	
	//[유저] 예약목록 조회
	@RequestMapping("mypage/mypage_reserve.do")
	public static String mypage_reserve(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		List<ReserveVO> list = ReserveDAO.reserveMypageData(id);
		
		request.setAttribute("list", list);
		request.setAttribute("mypage_jsp", "../mypage/mypage_reserve.jsp");
		request.setAttribute("main_jsp", "../mypage/mypage.jsp");
		return "../main/main.jsp";
	}
	//[관리자] 예약목록 조회
	@RequestMapping("adminpage/adminpage_reserve.do")
	public static String admin_reserve(HttpServletRequest request, HttpServletResponse response) {
		List<ReserveVO> list = ReserveDAO.reserveAdminpageData();
		request.setAttribute("list", list);
		request.setAttribute("admin_jsp","../adminpage/adminpage_reserve.jsp");
		request.setAttribute("main_jsp", "../adminpage/adminpage.jsp");
		return "../main/main.jsp";
	}
	//[관리자] 예약승인
	@RequestMapping("adminpage/reserve_ok.do")
	public static String admin_reserveOk(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		ReserveDAO.reserveAdminUpdate(Integer.parseInt(no));
		return "redirect:../adminpage/adminpage_reserve.do";
	}
	//[유저] 예약취소
	@RequestMapping("reserve/reserve_cancel.do")
	public static String reserve_cancel(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		ReserveDAO.reserveCancel(Integer.parseInt(no));
		return "redirect:../mypage/mypage_reserve.do";
	}
	//[유저] 예약식당 정보 조회
	@RequestMapping("reserve/reserve_info.do")
	public static String reserve_info(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		ReserveVO vo = ReserveDAO.reserveInfo(Integer.parseInt(no));
		request.setAttribute("vo", vo);
		return "../reserve/reserve_info.jsp";
	}
}
