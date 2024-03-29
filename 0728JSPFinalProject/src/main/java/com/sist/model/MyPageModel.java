package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.dao.MemberDAO;
import com.sist.vo.MemberVO;

import controller.Controller;
import controller.RequestMapping;

@Controller
public class MyPageModel {
   @RequestMapping("mypage/mypage.do")
   public String mypage_main(HttpServletRequest request,HttpServletResponse response)
   {
	   request.setAttribute("main_jsp", "../mypage/mypage.jsp");
	   return "../main/main.jsp";
   }
   @RequestMapping("member/join_update.do")
   public String member_join_update(HttpServletRequest request, HttpServletResponse response) {
	   HttpSession session = request.getSession();
	   String id = (String)session.getAttribute("id");
	   MemberVO vo = MemberDAO.memberDetailData(id);
	   String tel = vo.getTel();
	   tel = tel.substring(tel.indexOf("-")+1);
	   vo.setTel(tel);
	   request.setAttribute("vo", vo);
	   request.setAttribute("mypage_jsp", "../member/join_update.jsp");
	   request.setAttribute("main_jsp", "../mypage/mypage.jsp");
	   return "../main/main.jsp";
   }
   @RequestMapping("member/join_update_ok.do")
   public String member_join_update_ok(HttpServletRequest request, HttpServletResponse response) {
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
//	 		String birthday=request.getParameter("birthday");
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
//	 		vo.setBirthday(birthday);
	 		vo.setEmail(email);
	 		vo.setPost(post);
	 		vo.setAddr1(addr1);
	 		vo.setAddr2(addr2);
	 		vo.setTel(tel1+"-"+tel2);
	 		vo.setContent(content);
	 		
	 		//수정처리 요청
	 		boolean bCheck = MemberDAO.memberUpdate(vo);
	 		if(bCheck==true) {
	 			HttpSession session = request.getSession();
	 			session.setAttribute("name", vo.getName());
	 		}
	 		request.setAttribute("bCheck", bCheck);
	 		return "../member/join_update_ok.jsp";
   }
   
   @RequestMapping("member/join_delete.do")
   public String member_join_delete(HttpServletRequest request, HttpServletResponse response) {
	   
	   request.setAttribute("mypage_jsp", "../member/join_delete.jsp");
	   request.setAttribute("main_jsp", "../mypage/mypage.jsp");
	   return "../main/main.jsp";
   }
   
   @RequestMapping("member/join_delete_ok.do")
   public String member_join_delete_ok(HttpServletRequest request, HttpServletResponse response) {
	   HttpSession session = request.getSession();
	   String id =(String)session.getAttribute("id");
	   String pwd = request.getParameter("pwd");
	   
	   boolean bCheck = MemberDAO.memberDelete(id, pwd);
	   if(bCheck==true) {
		   session.invalidate();//로그아웃처리
	   }
	   request.setAttribute("bCheck", bCheck);
	   return "../member/join_delete_ok.jsp";
   }
}