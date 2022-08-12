package doodoo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import doodoo.model.DiaryModel;

@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.substring(request.getContextPath().length()+1);
		String jsp="";
		if(uri.equals("diary/diary.do")) {
			DiaryModel dm = new DiaryModel();
			jsp = dm.diary_main(request);
		}else if(uri.equals("diary/diary_ok.do")) {
			DiaryModel dm = new DiaryModel();
			jsp = dm.diary_ok(request);
		}
		RequestDispatcher rd = request.getRequestDispatcher(jsp);
		rd.forward(request, response);
	}

}
