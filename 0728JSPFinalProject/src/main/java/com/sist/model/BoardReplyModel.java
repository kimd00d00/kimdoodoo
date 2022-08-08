package com.sist.model;

import controller.Controller;
import controller.RequestMapping;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
import com.sist.vo.*;

@Controller
public class BoardReplyModel {
	@RequestMapping("board_reply/list.do")
	public String board_reply_list(HttpServletRequest request, HttpServletResponse response) {
		String page=request.getParameter("page");
		//사용자가 전송한 데이터는 모두 request 안에 들어 있습니다.
		if(page==null)
			page="1";
		int curPage= Integer.parseInt(page);
		Map map = new HashMap();
		int rowSize = 10;
		int start = (rowSize*curPage)-(rowSize-1);
		int end = rowSize*curPage;
		
		map.put("start",start);
		map.put("end", end);
		List<BoardReplyVO> list = BoardReplyDAO.boardReplyListData(map);
		int totalPage = BoardReplyDAO.boardReplyTotalPage();
		
		request.setAttribute("list", list);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("curPage",curPage);
		request.setAttribute("main_jsp", "../board_reply/list.jsp");
		return "../main/main.jsp"; //실질적으로 화면 출력 관리. main.jsp에 list.jsp 를 출력해라.
	}
	
	@RequestMapping("board_reply/insert.do")
	public String board_reply_insert(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("main_jsp", "../board_reply/insert.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("board_reply/insert_ok.do")
	public String board_reply_insert_ok(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		String name= request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		
		BoardReplyVO vo = new BoardReplyVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		BoardReplyDAO.boardReplyInsert(vo);
		
		return "redirect:../board_reply/list.do";
		//데이터 전송은 없고, 처리 후 이전에 실행된 화면으로 이동시킨다.
	}
	
}
