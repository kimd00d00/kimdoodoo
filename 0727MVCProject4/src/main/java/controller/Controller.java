package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import model.*;

@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> clsList = new ArrayList<String>();
	public void init(ServletConfig config) throws ServletException {
		try {
			String pkg = "model";
			String path = "C:\\Users\\user\\git\\sist\\0727MVCProject4\\src\\main\\java\\";
			String temp = path+"\\"+pkg.replace(".", "\\"); //doodoo.prjct.model 이런걸 doodoo\\prjct\\model로 바꿔줌
			File dir = new File(temp);
			File[] files = dir.listFiles(); //temp에 있는 모든 파일들 가져옴
			for(File f:files) {
//				System.out.println(f.getName());
				String s = f.getName();
				String ext = s.substring(s.lastIndexOf(".")+1); //확장자명 가져옴
				if(ext.equals("java")) {
					//System.out.println(s);
					String fp = pkg+"."+s.substring(0,s.lastIndexOf("."));//패키지 이름과 함께 클래스이름을 가져옴
					System.out.println(fp);
					clsList.add(fp);
					//읽어 오는 순서가 일정하지 않아 데이터를 맞추기가 힘들다. -> Map같은거 쓰셈
				}
			}
		}catch(Exception ex) {}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String cmd = request.getParameter("cmd");
			for(String ss:clsList) {
				//ss = model.ListModel
				//cmd = list
				//MODEL.LISTMODEL이 LIST를 포함하는가 확인했음ㅋㅋ이렇게하면안됨
				if(ss.toUpperCase().contains(cmd.toUpperCase())) {
					Class clsName = Class.forName(ss);
					if(clsName.isAnnotationPresent(Control.class)==false) 
						continue;
						//Annotation이 존재하는지 확인하고, 존재하지 않으면 메모리 할당을 하지 않겠다.
						//Model.java같은 인터페이스를 제외하겠다는 것임
					Object obj = clsName.getDeclaredConstructor().newInstance();
					Model m = (Model)obj;
					String jsp = m.execute(request);
					RequestDispatcher rd = request.getRequestDispatcher(jsp);
					rd.forward(request, response);
					return;
				}
			}
		}catch(Exception ex) {}
	}

}
