package controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.net.*;
import java.util.*;

//Mac과 Windows를 호환할 수 있는 XML 읽기
/*
 * XML 파싱방법
 * - JAXP : DOM / SAX (MyBatis, Spring)
 * 			DOM : 메모리에 트리 형태로 저장 후 처리한다 (수정, 삭제, 추가)
 * 			SAX : 한 줄씩 태그의 값을 읽어 온다 (읽기 전용)
 * 			 => XML
 * 				데이터 저장 위치 -> <태그>데이터</태그> <태그 속성="데이터">
 * - JAXB : 빅데이터 (클래스 변수 == 태그명) ==> 1.8까지 지원
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> pkgList = new ArrayList<String>(); //XML에서 패키지를 읽어 옴
	private List<String> clsList = new ArrayList<String>(); //패키지 안의 클래스를 모은다
	public void init(ServletConfig config) throws ServletException {
		try {
			URL url = this.getClass().getClassLoader().getResource(".");//resource에 .을 주면 현재 폴더를 의미한다.
			
			File file = new File(url.toURI());
			System.out.println(file.getPath());
			//C:\webDev\webStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\0727SpringMVCProject\WEB-INF\classes
			//현재 경로명을 읽어 오는 것.. 그래야 경로명 지금처럼 읽어 올 필요가 없음.
			
			String path = file.getPath();
			path = path.replace("\\", File.separator); //Mac은 /로 Windows는 \\로 바꿔준다!!!(공동작업용)
			System.out.println(path);
			//C:\webDev\webStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\0727SpringMVCProject\WEB-INF\classes
			path = path.substring(0,path.lastIndexOf(File.separator));
			System.out.println(path);
			//C:\webDev\webStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\0727SpringMVCProject\WEB-INF
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File(path+File.separator+"application.xml"));//윈도우면 \\, 맥이면 /
			
			Element rootTag = doc.getDocumentElement();
			System.out.println(rootTag.getTagName());
			//beans -> XML을 읽었다~
			
			NodeList list = rootTag.getElementsByTagName("component-scan");
			for(int i=0; i<list.getLength(); i++) {
				Element cs = (Element)list.item(i);//cs:component-scan
				String value = cs.getAttribute("base-package");
				pkgList.add(value); 
			}
			for(String s:pkgList) {
				System.out.println(s); //패키지명이 출력됨
				path = path.substring(0,path.lastIndexOf(File.separator));
				System.out.println(path);
				//C:\webDev\webStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\0727SpringMVCProject
				String ss = path+File.separator+"WEB-INF"+File.separator+"classes"+File.separator+s.replace(".", File.separator);
				File dir = new File(ss);
				File[] files = dir.listFiles();
				for(File f:files) {
//					System.out.println(f.getName());
					String sss = s + "." + f.getName().substring(0,f.getName().lastIndexOf("."));
					//model.ListModel.class에서 .class를 떼려구
					System.out.println(sss);
				}
			}
			
			
		}catch(Exception ex) {}
	}
	//Model 등록
	//Model을 찾아서 요청 수행 => JSP찾기 => request, session으로 전송
	protected void service(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		try {
			// 사용자 URL - http://localhost:8080/SpringMVCProject/main/main.do
			String uri = request.getRequestURI(); // /SpringMVCProject/main/main.do
			uri = uri.substring(request.getContextPath().length()+1); // main/main.do
			
			//Model 클래스 찾기
			for(String strCls:clsList) {
				Class clsName = Class.forName(strCls);
				if(clsName.isAnnotationPresent(Controller.class)==false)
					continue; //클래스 위에 @Controller 있는지 확인하고 없으면 제외
				//메모리 할당
				Object obj = clsName.getDeclaredConstructor().newInstance();
				//요청 처리
				Method[] methods = clsName.getDeclaredMethods(); //Model클래스에 선언된 모든 메서드를 가져 온다.
				for(Method m:methods) {
					RequestMapping rm = m.getAnnotation(RequestMapping.class); 
					//메서드 위에 @RequestMapping 이 있는지 확인
					if(uri.equals(rm.value())) { //처리 메서드를 찾아서 수행
						String jsp = (String)m.invoke(obj, request, response);
						/*
						 * Model 메서드는 앞으로 이렇게 만들것임
						 * @RequestMapping("uri")
						 * public String main_main(HttpServletRequest request, HttpServletResponse response){
						 * 		return "출력할 JSP";
						 * request : 요청값을 받아 처리해서 setAttribute()로 넘겨줌
						 * response : Cookie, 파일 업로드 등의 응답
						 * }
						 */
						//이동은 sendRedirect, forward로 진행
						//sendRedirect : 기존에 만들어진 파일로 이동/재호출 , request를 초기화 
						//forward : 화면 출력, request 보존해서 전송
						if(jsp.startsWith("redirect")){
							//return "redirect:../main/main.do" 이렇게 보내주고
							response.sendRedirect(jsp.substring(jsp.indexOf(":")+1)); //../main/main.do
						}else {
							// return "../main/main.jsp" 이렇게 보내주기로 약속합시다(사유:스프링에서 이렇게 함)
							RequestDispatcher rd = request.getRequestDispatcher(jsp);
							rd.forward(request,response); //jsp 한테 request와 response를 보내라~
						}
						return;
					}
				}
				
			}
		}catch(Exception ex) {}
	}
}
