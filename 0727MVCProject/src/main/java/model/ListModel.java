package model;

import javax.servlet.http.HttpServletRequest;
//Model의 역할 : 요청 처리 -> 그 요청이 request에 들어 있다~
public class ListModel {
	public void execute(HttpServletRequest request) {
		request.setAttribute("msg", "게시판 목록");
	}
}
