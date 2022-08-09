package doodoo.controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Annotation을 찾아 오자.
		String uri = request.getRequestURI();
		uri = uri.substring(request.getContextPath().length()+1);
		//이렇게.. emp/detail.do 라는 이름을 가져왔음.
		try {
			Class clsName = Class.forName("doodoo.model.EmpModel");
			Object obj = clsName.getDeclaredConstructor().newInstance();
			Method[] methods = clsName.getDeclaredMethods();
			
			for(Method m:methods) {
				RequestMapping rm = m.getAnnotation(RequestMapping.class);
				System.out.println("RequestMapping.class="+RequestMapping.class); //RequestMapping.class=interface doodoo.controller.RequestMapping
				System.out.println("rm.value()="+rm.value()); //rm.value()=emp/list.do
				if(rm.value().equals(uri)) {
					String jsp=(String)m.invoke(obj, request);
					RequestDispatcher rd = request.getRequestDispatcher(jsp);
					rd.forward(request, response);
					return;
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
