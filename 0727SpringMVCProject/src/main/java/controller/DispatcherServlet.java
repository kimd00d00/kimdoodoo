package controller;

import java.io.File;
import java.io.IOException;
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

@WebServlet("/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> pkgList = new ArrayList<String>();
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
				File dir = new File(path+File.separator+"WEB-INF"+File.separator+"classes"+File.separator+s.replace(".", File.separator));
				File[] files = dir.listFiles();
				for(File f:files) {
					System.out.println(f.getName());
				}
			}
			
			
		}catch(Exception ex) {}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		
	}

}
