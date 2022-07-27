package controller;
//Dispatcher이 컨트롤러 이름임
import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import model.*;

@WebServlet("*.do") 
//내가 마음대로 둬도된다. 
//*은 그 자리에 어떤 단어가 들어와도 이 서블릿(DispatcherServelt)을 찾아줄 수 있다는 뜻
//list.do, find.do 등.. 다 DispatcherServelt을 찾아줄 수 있게 된다.
//URI가 do로 끝나지 않으면 "요청된 리소스 [/0727MVCProject5/food/category.ex]은(는) 가용하지 않습니다." 뜸
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<String> clsList = new ArrayList<String>();
	
	public void init(ServletConfig config) throws ServletException {
		clsList.add("model.FoodModel");
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//사용자가 요청한 URL을 읽어 온다. (맨 뒤에 .do)
			String uri = request.getRequestURI();
			uri = uri.substring(request.getContextPath().length()+1);
			//http://localhost:8080/0727MVCProject5/food/category.do 이 전체는 URL
			//URI는 /0727MVCProject5/category.do 부분만.
			//ContextPath는 0727MVCProject
			//?no=1 이런거는 request에 담겨 오는것임. request.getRequestURI()에서 제외됨
			System.out.println(uri); //food/category.do
			//이 URI와 RequestMapping 이름이랑 같으면 호출하라! 고 시키는 것임
			
			for(String cls:clsList) {
				Class clsName = Class.forName(cls);
				//Controller도 꼭 붙여줘야함
				if(clsName.isAnnotationPresent(Controller.class)==false)
					continue;
				Object obj = clsName.getDeclaredConstructor().newInstance();//생성자를 가져와서 메모리 할당을 하겠다~
				//메서드 찾기
				Method[] methods = clsName.getDeclaredMethods(); //이 안에 있는 메서드를 모두 가져와라.
				for(Method m:methods) {
					//메서드에 있는 어노테이션을 가져온다. 
					//(어노테이션은 한 번에 여러 개도 올라갈 수 있음)
					RequestMapping rm = m.getAnnotation(RequestMapping.class);
					if(uri.equals(rm.value())){
						//rm이 갖고있는 값
						String jsp = (String)m.invoke(obj, request);
						//obj가 가진 메서드를 찾아서 호출한다.
						RequestDispatcher rd = request.getRequestDispatcher(jsp);
						rd.forward(request, response);
						
						return;
					}
				}
			}
		}catch(Exception ex) {}
	}

}
