package doodoo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import doodoo.dao.*;
import doodoo.model.*;

@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.substring(request.getContextPath().length()+1);
		String jsp="";
		MovieModel mm = new MovieModel();
		if(uri.equals("movie/list.do")) {
			mm.movie_list(request, response);
			jsp="../movie/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(jsp);
			rd.forward(request, response);
		}else if(uri.equals("movie/detail_before.do")) {
			mm.movie_detail_before(request, response);
		}else if(uri.equals("movie/detail.do")) {
			mm.movie_deatil(request, response);
			jsp="../movie/detail.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(jsp);
			rd.forward(request, response);
		}
		
		
	}

}
