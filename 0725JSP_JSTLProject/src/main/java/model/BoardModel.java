package model;

import java.net.http.HttpRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.*;
public class BoardModel {
	public void boardListData(HttpServletRequest request) {
		//매개변수 전송할 때 call by reference방식임
		//->jsp가 보내는 request의 주소와 boardListData()가 받는 request의 주소가 동일하다.
		//원래는 jsp 상단에서 <%%> 안에 했는데 그러면 재사용이 안되고 비슷한 코드가 반복되며 복잡함.. Model에서 처리하는게 좋음!
		ReplyBoardDAO dao = new ReplyBoardDAO();
		String page = request.getParameter("page");
		if(page==null) page="1";
		
		int curPage = Integer.parseInt(page);
		List<ReplyBoardVO> list = dao.boardListData(curPage);
		int count = dao.boardRowCount();
		int totalPage = (int)(Math.ceil(count/10.0));
		count = count - ((curPage*10)-10);
		
		//JSP로 출력에 필요한 결과값 전송
		request.setAttribute("curPage", curPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("msg", "관리자가 삭제한 게시물입니다");
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		request.setAttribute("today", today);
	}
	
	//글쓰기
	public void boardInsert(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex) {
			System.out.println("Model:boardInsert() Error!");
			ex.printStackTrace();
		}
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		
		ReplyBoardVO vo = new ReplyBoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		ReplyBoardDAO dao = new ReplyBoardDAO();
		dao.boardInsert(vo);
		try {
			response.sendRedirect("list.jsp");
		}catch(Exception ex) {
			System.out.println("redirect Error!");
			ex.printStackTrace();
		}
		//jsp는 화면에 출력하는 기능만 가지고 있고 모든 처리는 자바가 한다.
	}
	
	//상세보기
	public void boardDetailData(HttpServletRequest request) {
		//이동하는 게 아니라서 response는 필요 없다.
		//detail.jsp?no=${vo.no} 로 넘겨줬음!
		String no = request.getParameter("no");
		ReplyBoardDAO dao = new ReplyBoardDAO();
		ReplyBoardVO vo = dao.boardDetail(Integer.parseInt(no));
		
		//JSP로 전송~
		request.setAttribute("vo", vo);
		//JSP에서 요청함(click, 입력후 버튼 등) -> Model이 받아서 처리 -> 결과값을 JSP로 보냄 -> JSP가 출력
	}
	
	//답변달기
	public void boardReplyInsert(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex) {
			System.out.println("Model:boardInsert() Error!");
			ex.printStackTrace();
		}
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		String pno = request.getParameter("pno"); //상위 게시물번호 -> vo에 첨부하면 안됨

		ReplyBoardVO vo = new ReplyBoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		//DAO 연동
		ReplyBoardDAO dao = new ReplyBoardDAO();
		dao.replyInsert(Integer.parseInt(pno), vo);
		//화면 이동
		try{
			response.sendRedirect("list.jsp");
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Model:boardReplyInsert():sendRedirect() Error!");
		}
	}
	
	//수정하기
	public void boardUpdateDate(HttpServletRequest request) {
		//"update.jsp?no=${vo.no }"로 넘겨줬음
		String no = request.getParameter("no");
		//DAO 데이터 읽기
		ReplyBoardDAO dao = new ReplyBoardDAO();
		ReplyBoardVO vo = dao.boardUpdateData(Integer.parseInt(no));
		//읽은 데이터를 request에 담아 update.jsp로 넘겨 준다.
		request.setAttribute("vo", vo);
	}
	
	public void boardUpdateOk(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex) {
			System.out.println("Model:boardUpdateOk() Error!");
			ex.printStackTrace();
		}
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		String no = request.getParameter("no");

		ReplyBoardVO vo = new ReplyBoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		vo.setNo(Integer.parseInt(no));
		
		//DAO 연동
		ReplyBoardDAO dao = new ReplyBoardDAO();
		//결과값 전송
		boolean bCheck = dao.boardUpdate(vo);
		request.setAttribute("bCheck", bCheck);
		request.setAttribute("no", no);
	}
	
	public void boardDelete(HttpServletRequest request) {
		String no = request.getParameter("no");
		String pwd = request.getParameter("pwd");
		//DAO 연동
		ReplyBoardDAO dao = new ReplyBoardDAO();
		//결과값을 request에 담는다.
		String res = dao.boardDelete(Integer.parseInt(no),pwd);
		request.setAttribute("res", res);
		
	}
}