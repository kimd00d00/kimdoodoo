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
		System.out.println("사용자 요청 진입");
//		EmpModel model = new EmpModel();
		System.out.println("요청을 처리합니다");
//		model.empListData(request);
		try {
			Class clsName=Class.forName("doodoo.model.EmpModel");
			Object obj = clsName.getDeclaredConstructor().newInstance();
			Method[] methods = clsName.getDeclaredMethods();
			methods[0].invoke(obj, request);
			//메서드명을 마음대로 만들 수 있다.
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("요청 끝. request에 결과값을 채웁니다");
		//request를 JSP로 전송
		RequestDispatcher rd = request.getRequestDispatcher("emp.jsp");
		rd.forward(request, response);
	}


}
