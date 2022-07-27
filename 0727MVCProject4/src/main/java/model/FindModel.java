package model;

import javax.servlet.http.HttpServletRequest;

import controller.Control;
//새로 추가된 Model
@Control
public class FindModel implements Model {

	@Override
	public String execute(HttpServletRequest request) {
		request.setAttribute("msg", "게시물 찾기");
		return "view/find.jsp";
	}

}
