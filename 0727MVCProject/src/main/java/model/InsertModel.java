package model;

import javax.servlet.http.HttpServletRequest;

public class InsertModel {
	public void execute(HttpServletRequest request) {
		request.setAttribute("msg", "게시물 등록");
	}
}
