package model;

import javax.servlet.http.HttpServletRequest;

public class UpdateModel implements Model{
	@Override
	public String execute(HttpServletRequest request) {
		request.setAttribute("msg", "게시물 수정");
		return "view/update.jsp";
	}
}