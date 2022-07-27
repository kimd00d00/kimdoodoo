package model;

import javax.servlet.http.HttpServletRequest;

//Interface : 기능이 유사한 클래스를 모아서 한 번에 관리
public interface Model {
	public String execute(HttpServletRequest request);
}
