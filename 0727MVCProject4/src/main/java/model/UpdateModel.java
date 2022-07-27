package model;

import javax.servlet.http.HttpServletRequest;

import controller.Control;
@Control
public class UpdateModel implements Model{
	@Override
	public String execute(HttpServletRequest request) {
		request.setAttribute("msg", "게시물 수정");
		return "view/update.jsp";
	}
}