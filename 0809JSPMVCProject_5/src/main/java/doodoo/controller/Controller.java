package doodoo.controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import doodoo.model.*;

@WebServlet("*.do")
//.do로 끝나는 주소를 치면 이 서블릿이 실행된다.
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//URI를 가져온다.
		String uri = request.getRequestURI();
		System.out.println(uri); ///0809JSPMVCProject_5/emp/list.do
		String cont = request.getContextPath();
		System.out.println(cont); ///0809JSPMVCProject_5
		uri = uri.substring(request.getContextPath().length()+1);
		System.out.println(uri); //emp/list.do
		
		EmpModel model = new EmpModel();
		
		String jsp = "";
		if(uri.equals("emp/list.do")) {
			model.empListData(request);
			jsp="../emp/list.jsp";
		}else if(uri.equals("emp/detail.do")) {
			model.empDetailData(request);
			jsp="../emp/detail.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(jsp);
		rd.forward(request, response);
	}


}
