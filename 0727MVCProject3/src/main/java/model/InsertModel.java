package model;

import javax.servlet.http.HttpServletRequest;

public class InsertModel implements Model{
	@Override
	public String execute(HttpServletRequest request) {
		request.setAttribute("msg", "게시물 등록");
		return "view/insert.jsp";
	}
}