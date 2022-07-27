package controller;

import java.io.IOException;

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

	public void init(ServletConfig config) throws ServletException {
		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		//사용자의 요청값을 받아 온다. --> /Controller?cmd=사용자요청값 이렇게 받아올것이다.
		String cmd = request.getParameter("cmd");
		//요청 처리를 위해 Model을 찾아 온다.(Model에 request를 보낸다)
		String jsp = "";
		if(cmd.equals("list")) {
			ListModel model = new ListModel();
			model.execute(request); //이러면 request의 msg에 값을 채워줄 것임.
			jsp = "list.jsp";
		}else if(cmd.equals("update")){
			UpdateModel model = new UpdateModel();
			model.execute(request);
			jsp = "update.jsp";
		}else if(cmd.equals("delete")){
			DeleteModel model = new DeleteModel();
			model.execute(request);
			jsp = "delete.jsp";
		}else if(cmd.equals("insert")){
			InsertModel model = new InsertModel();
			model.execute(request);
			jsp = "insert.jsp";
		}
		//요청 처리가 되면 결과값을 받아 온다.(request.setAttribute)
		//request를 jsp 페이지에 전송한다.
		RequestDispatcher rd = request.getRequestDispatcher("view/"+jsp); //전송 담당!
		rd.forward(request, response);
	}

}
