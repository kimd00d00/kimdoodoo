package model;
// EL/JSTL -> MVC용임 -> JSP에서 자바 코딩을 최소화
import javax.servlet.http.HttpServletRequest;
import dao.*;
import java.util.*;
public class SeoulModel {
	public void locationListData(HttpServletRequest request) {
		LocationDAO dao = new LocationDAO();
		String strPage = request.getParameter("page");
		if(strPage==null)
			strPage="1";
		int curPage = Integer.parseInt(strPage);
		List<LocationVO> list = dao.locationListData(curPage);
		//총페이지
		int totalPage = dao.locationTotalPage();
		
		final int BLOCK=5;
		int startPage = ((curPage-1)/BLOCK*BLOCK)+1;
		int endPage = ((curPage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalPage)
			endPage = totalPage;
		request.setAttribute("curPage", curPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("list",list);
	}
	
	public void locationDetailData(HttpServletRequest request) {
		LocationDAO dao = new LocationDAO();
		String no = request.getParameter("no");
		LocationVO vo = dao.locationDetailData(Integer.parseInt(no));
		request.setAttribute("vo", vo); //얘가 항상 있어줘야함
	}
	public void natureListData(HttpServletRequest request) {
		LocationDAO dao = new LocationDAO();
		String strPage = request.getParameter("page");
		if(strPage==null)
			strPage="1";
		int curPage = Integer.parseInt(strPage);
		List<LocationVO> list = dao.natureListData(curPage);
		//총페이지
		int totalPage = dao.natureTotalPage();
		
		final int BLOCK=5;
		int startPage = ((curPage-1)/BLOCK*BLOCK)+1;
		int endPage = ((curPage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalPage)
			endPage = totalPage;
		request.setAttribute("curPage", curPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("list",list);
	}
	
	public void natureDetailData(HttpServletRequest request) {
		LocationDAO dao = new LocationDAO();
		String no = request.getParameter("no");
		LocationVO vo = dao.natureDetailData(Integer.parseInt(no));
		request.setAttribute("vo", vo); //얘가 항상 있어줘야함
	}
}
