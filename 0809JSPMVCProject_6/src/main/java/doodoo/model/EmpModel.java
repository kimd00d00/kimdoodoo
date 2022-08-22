package doodoo.model;

import java.util.*;
import javax.servlet.http.HttpServletRequest;

import doodoo.controller.RequestMapping;
import doodoo.dao.*;

public class EmpModel {
	@RequestMapping("emp/list.do")
	public String empListData(HttpServletRequest request) {
		EmpDAO dao = new EmpDAO();
	 	List<EmpVO> list = dao.empListData();
	 	//list를 jsp로 전송
	 	request.setAttribute("list", list);
	 	return "../emp/list.jsp"; //jsp의 경로를 리턴한다.
	}
	@RequestMapping("emp.detail.do")
	public String empDetailData(HttpServletRequest request) {
		EmpDAO dao = new EmpDAO();
		String empno = request.getParameter("empno");
		System.out.println(empno);
		EmpVO vo = dao.empDetailData(Integer.parseInt(empno));
		//list를 jsp로 전송
		request.setAttribute("vo",vo);
		return "..emp/detail.jsp";
	}
}
