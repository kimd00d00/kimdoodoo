package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.RequestMapping;

import com.sist.dao.*;

@Controller
public class MemberModel {
	//회원가입-아이디 중복체크
	@RequestMapping("member/idcheck.do")
	public String member_idcheck(HttpServletRequest request, HttpServletResponse response) {
		//화면 출력만 해줌
		return "../member/idcheck.jsp";
	}
	@RequestMapping("member/idcheck_ok.do")
	public String member_idcheck_ok(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		int count = MemberDAO.memberIdCheck(id);
		request.setAttribute("count", count);
		return "../member/idcheck_ok.jsp";
	}
	@RequestMapping("member/emailcheck_ok.do")
	public String member_emailcheck_ok(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		int count = MemberDAO.memberEmailCheck(email);
		request.setAttribute("count", count);
		return "../member/idcheck_ok.jsp";
	}
	//화면 출력만 해줌
	@RequestMapping("member/login.do")
	public String member_login(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("login");
		return "../member/login.jsp"; //main에 출력하는거 아님! shadowBox에 올릴것임. -> shadowBox 안에서 처리를 해야 한다.
	}
	@RequestMapping("member/join.do")
	public String member_join(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("main_jsp", "../member/join.jsp");
		return "../main/main.jsp";
	}
}
