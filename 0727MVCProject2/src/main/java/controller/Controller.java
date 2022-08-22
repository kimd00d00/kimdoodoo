package controller;

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


@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map clsMap = new HashMap(); //ClassMap
	
	public void init(ServletConfig config) throws ServletException {
		//if문을 없앤 것이다!
		clsMap.put("list", new ListModel());
		clsMap.put("insert", new InsertModel());
		clsMap.put("update", new UpdateModel());
		clsMap.put("delete", new DeleteModel());
		//나중엔 이걸 xml로 등록해서 읽어올 것임(=Spring)
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
