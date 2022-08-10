package doodoo.model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;
import doodoo.dao.*;

public class MovieModel {
	public void movie_list(HttpServletRequest request, HttpServletResponse response) {
		String cno = request.getParameter("cno");
		if(cno==null)
			cno="1";
		String strPage = request.getParameter("page");
		if(strPage==null)
			strPage="1";
		int curPage = Integer.parseInt(strPage);
		MovieDAO dao = new MovieDAO();
		List<MovieVO> list = dao.movieListData(Integer.parseInt(cno), curPage);
		int totalPage = dao.movieTotalPage(Integer.parseInt(cno));
		
		//쿠키 읽어오기
		Cookie[] cookies = request.getCookies();
		List<MovieVO> cList = new ArrayList<MovieVO>();
		if(cookies!=null){
			for(int i=cookies.length-1;i>=0;i--){
				if(cookies[i].getName().startsWith("movie")){
					String mno = cookies[i].getValue();
					MovieVO vo = dao.movieDetailData(Integer.parseInt(mno));
					cList.add(vo);
				}
			}
		}
		request.setAttribute("cno", cno);
		request.setAttribute("cList", cList);
		request.setAttribute("curPage", curPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("list", list);
	}
	public void movie_detail_before(HttpServletRequest request, HttpServletResponse response) {
		String mno = request.getParameter("mno");
		Cookie cookie = new Cookie("movie"+mno, mno);
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		//클라이언트로 전송
		response.addCookie(cookie);
		try {
			response.sendRedirect("detail.do?mno="+mno);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void movie_deatil(HttpServletRequest request, HttpServletResponse response) {
		String mno = request.getParameter("mno"); //detail_before에서 detail.jsp?mno=로 보내주었음.
		MovieDAO dao = new MovieDAO(); 
		MovieVO vo = dao.movieDetailData(Integer.parseInt(mno));
		request.setAttribute("vo", vo);
	}
}
