package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
}
