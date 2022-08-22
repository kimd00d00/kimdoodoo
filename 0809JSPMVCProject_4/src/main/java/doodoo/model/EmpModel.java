package doodoo.model;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import doodoo.dao.*;
public class EmpModel {
	public void empListData(HttpServletRequest request) {
		EmpDAO dao = new EmpDAO();
	 	List<EmpVO> list = dao.empListData();
	 	//list를 jsp로 전송
	 	request.setAttribute("list", list);
	}
}
