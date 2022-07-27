package model;

import javax.servlet.http.HttpServletRequest;

public class ListModel implements Model{
	@Override
	public String execute(HttpServletRequest request) {
		request.setAttribute("msg", "게시물 목록");
		return "view/list.jsp"; //request가 view/list.jsp로 넘어갈 거다.
	}
}