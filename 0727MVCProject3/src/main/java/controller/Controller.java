package controller;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;
import javax.xml.parsers.*;//XML 파싱(데이터 읽어오기)을 위한 패키지
import org.w3c.dom.*;//태그 제어 프로그램


@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map clsMap = new HashMap(); //key와 클래스를 저장할 것
	
	public void init(ServletConfig config) throws ServletException {
		//xml파일의 properties에서 Path를 가져 온다.
		String path = "C:\\Users\\user\\git\\sist\\0727MVCProject3\\src\\main\\webapp\\WEB-INF\\app.xml"; 
		try {
			//Parser 생성 : XML, WML, HTML 종류별로 Parser 생성가능
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
			//Parser
			DocumentBuilder db = dbf.newDocumentBuilder();
			//Parsing된 데이터 저장
			Document doc = db.parse(new File(path));
			//root 태그 읽기(<beans>를 읽어 그 아래 요소를 가져오겠다) - 항상 최상위 태그 이름을 가져와야 함
			Element root = doc.getDocumentElement();
			System.out.println(root.getTagName()); //beans
			
			//for문을 돌리기 위해 같은 태그를 묶는다
			NodeList list = root.getElementsByTagName("bean"); //bean태그를 묶어라~
			
			for(int i=0;i<list.getLength();i++) {
				Element beanTag = (Element)list.item(i);
				String id = beanTag.getAttribute("id");
				String cls = beanTag.getAttribute("class");
				System.out.println(id+"/"+cls); //list/ListModel 이렇게 출력
				//Map에 저장하자
				Class clsName = Class.forName(cls);
				Object obj = clsName.getDeclaredConstructor().newInstance(); //클래스 이름으로 메모리 할당
				clsMap.put(id, obj);
				System.out.println(id+":"+obj);
			}
			
		}catch(Exception ex) {}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		//Model을 찾자
		Model model = (Model)clsMap.get(cmd); //if문을 와바박 써 줄 필요가 없어졌다~
		//이제 JSP를 찾자
		String jsp = model.execute(request);
		//request를 전송하자
		RequestDispatcher rd = request.getRequestDispatcher(jsp);
		rd.forward(request, response);
	}

}
