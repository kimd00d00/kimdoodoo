package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.dao.ReplyDAO;
import com.sist.vo.ReplyVO;

import controller.Controller;
import controller.RequestMapping;

@Controller
public class ReplyModel {
	@RequestMapping("reply/reply_insert.do")
	public String reply_insert(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8"); //한글 변환
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		String bno = request.getParameter("bno");//게시물 번호
		String type = request.getParameter("type");//카테고리
		String msg = request.getParameter("msg");//댓글 내용
		//id/name은 session, bno/type/msg는 request에 저장돼있음.
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String name = (String)session.getAttribute("name");
		ReplyVO vo = new ReplyVO();
		vo.setBno(Integer.parseInt(bno));
		vo.setId(id);
		vo.setName(name);
		vo.setMsg(msg);
		vo.setType(Integer.parseInt(type));
		
		String[] tmp = {"","project_freeboard","seoul_location","seoul_nature","seoul_shop"};
		String table=tmp[Integer.parseInt(type)];
		vo.setTable_name(table);
		//DAO->오라클로 전송
		ReplyDAO.replyInsert(vo);
		return "redirect:../freeboard/detail.do?no="+bno;
	}
	@RequestMapping("reply/reply_delete.do")
	public String reply_delete(HttpServletRequest request, HttpServletResponse response) {
		String bno=request.getParameter("bno"); //요청할 때 bno를 같이 보내놔야 redirect할 수 있음
		String no = request.getParameter("no"); //댓글번호
		ReplyDAO.replyDelete(Integer.parseInt(no));
		return "redirect:../freeboard/detail.do?no="+bno;
	}
	@RequestMapping("reply/reply_update.do")
	public String reply_update(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		String bno = request.getParameter("bno"); //글번호
		String no = request.getParameter("no"); //댓글번호
		String msg = request.getParameter("msg");
		ReplyVO vo = new ReplyVO();
		vo.setBno(Integer.parseInt(bno));
		vo.setNo(Integer.parseInt(no));
		vo.setMsg(msg);
		ReplyDAO.replyUpdate(vo);
		
		return "redirect:../freeboard/detail.do?no="+bno;
	}
}
