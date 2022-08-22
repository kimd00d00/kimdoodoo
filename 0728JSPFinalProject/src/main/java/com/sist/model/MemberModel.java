package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.RequestMapping;

import com.sist.dao.*;
import com.sist.vo.MemberVO;

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
	@RequestMapping("member/tel_check.do")
	public String member_telcheck(HttpServletRequest request, HttpServletResponse response) {
		String tel = request.getParameter("tel");
		int count = MemberDAO.memberTelCheck(tel);
		request.setAttribute("count", count);
		return "../member/idcheck_ok.jsp";
	}
	//화면 출력만 해줌
	@RequestMapping("member/login.do")
	public String member_login(HttpServletRequest request, HttpServletResponse response) {
		return "../member/login.jsp"; //main에 출력하는거 아님! shadowBox에 올릴것임. -> shadowBox 안에서 처리를 해야 한다.
	}
	@RequestMapping("member/join.do")
	public String member_join(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("main_jsp", "../member/join.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("member/join_ok.do")
	public String member_join_ok(HttpServletRequest request, HttpServletResponse response) {
		//사용자 전송값 받기
		try {
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		String name=request.getParameter("name");
		String sex=request.getParameter("sex");
		String birthday=request.getParameter("birthday");
		String email=request.getParameter("email");
		String post=request.getParameter("post");
		String addr1=request.getParameter("addr1");
		String addr2=request.getParameter("addr2");
		String tel1=request.getParameter("tel1");
		String tel2=request.getParameter("tel2");
		String content=request.getParameter("content");
		
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPwd(pwd);
		vo.setName(name);
		vo.setSex(sex);
		vo.setBirthday(birthday);
		vo.setEmail(email);
		vo.setPost(post);
		vo.setAddr1(addr1);
		vo.setAddr2(addr2);
		vo.setTel(tel1+"-"+tel2);
		vo.setContent(content);
		//데이터베이스 연결+요청 처리
		MemberDAO.memberInsert(vo);
		//화면 이동
		return "redirect:../main/main.do";//redirect는 .do로 보낸다. 
		//이 처리로 인해 보여줄 게 없고 새로운 request를 받아 와야 할 때...(자바를 거쳐야 할 때) 화면만 이동할 때.
		//insert나 update는 redirect로 보낸다. 왜? -> 화면 출력이 필요 없음 -> request 보존할 필요 없음 
		//forward 화면 안에 출력할 내용이 있음 -> request 보존해야함
	}
	
	@RequestMapping("member/login_ok.do")
	public String member_login_ok(HttpServletRequest request, HttpServletResponse response) {
		//사용자 요청값을 받아온다.
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		//DAO연동 -> mapper로 SQL 만들어놓고 DAO에서 메서드 처리
		MemberVO vo = MemberDAO.isLogin(id, pwd);
		String result=vo.getMsg();
		if(result.equals("OK")) {
			HttpSession session = request.getSession(); //세션에 저장해야 로그인 유지가 가능함
			session.setAttribute("id", vo.getId());
			session.setAttribute("name", vo.getName());
			session.setAttribute("admin", vo.getAdmin());
		}
		request.setAttribute("result", result);
		return "../member/login_ok.jsp";//ajax에서 읽어갈것임
	}
	@RequestMapping("member/logout.do")
	public String member_logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate(); //session에 저장된 모든 데이터를 지운다.
		return "redirect:../main/main.do";
	}
}
