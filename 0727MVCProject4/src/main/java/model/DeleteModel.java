package model;

import javax.servlet.http.HttpServletRequest;

import controller.Control;
@Control
public class DeleteModel implements Model{
	@Override
	public String execute(HttpServletRequest request) {
		request.setAttribute("msg", "게시물 삭제");
		return "view/delete.jsp";
	}
}