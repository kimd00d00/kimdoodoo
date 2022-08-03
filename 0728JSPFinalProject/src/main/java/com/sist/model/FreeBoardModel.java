package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;
import java.util.*;

@Controller
public class FreeBoardModel {
	@RequestMapping("freeboard/list.do")
	public String freeboard_list(HttpServletRequest request, HttpServletResponse response) {
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curPage = Integer.parseInt(page);
		Map map = new HashMap();
		int rowSize = 10;
		int start = (rowSize*curPage)-(rowSize-1);
		int end = rowSize*curPage;
		map.put("start", start);
		map.put("end", end);
		
		List<FreeBoardVO> list = FreeBoardDAO.boardListData(map);
		int totalPage = FreeBoardDAO.boardTotalPage();
		//그렇게 글 수가 많지 않기 때문에 블록처리 하지 않을 것임.
		request.setAttribute("list", list);
		request.setAttribute("curPage", curPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("main_jsp", "../freeboard/list.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("freeboard/insert.do")
	public String freeboard_insert(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("main_jsp", "../freeboard/insert.jsp"); 
		return "../main/main.jsp";
	}
	
	@RequestMapping("freeboard/insert_ok.do")
	public String freeboard_insert_ok(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		String name = request.getParameter("name");
		System.out.println(name);
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		
		FreeBoardVO vo = new FreeBoardVO();
		vo.setName(name);
		vo.setContent(content);
		vo.setSubject(subject);
		vo.setPwd(pwd);
		FreeBoardDAO.boardInsert(vo);
		
		return "redirect:../freeboard/list.do"; //보내고 나서 request 에 있는 내용 지우기
	}
	
	@RequestMapping("freeboard/detail.do")
	public String freeboard_detail(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		FreeBoardVO vo = FreeBoardDAO.boardDetailData(Integer.parseInt(no));
		
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../freeboard/detail.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("freeboard/update.do")
	   public String freeboard_update(HttpServletRequest request,HttpServletResponse response){
		String no=request.getParameter("no");
		FreeBoardVO vo=FreeBoardDAO.boardUpdateData(Integer.parseInt(no));
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../freeboard/update.jsp");
		return "../main/main.jsp";
	   }
	
	@RequestMapping("freeboard/pwd_check.do")
	public String freeboard_pwd_check(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no"); //ajax에서 보낸 no 와  pwd 
		String pwd = request.getParameter("pwd");
		String res = FreeBoardDAO.boardPwdCheck(Integer.parseInt(no), pwd);
		request.setAttribute("res", res);
		return "../freeboard/update_ok.jsp"; //여기서 처리할 것
	}
	
	@RequestMapping("freeboard/update_ok.do")
	public String freeboard_update_ok(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		String name = request.getParameter("name");
		System.out.println(name);
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		String no = request.getParameter("no");
		
		FreeBoardVO vo = new FreeBoardVO();
		vo.setName(name);
		vo.setContent(content);
		vo.setSubject(subject);
		vo.setPwd(pwd);
		vo.setNo(Integer.parseInt(no));
		FreeBoardDAO.boardUpdate(vo);
		
		return "redirect:../freeboard/detail.do?no="+no; //보내고 나서 request 에 있는 내용 지우기
	}
}
