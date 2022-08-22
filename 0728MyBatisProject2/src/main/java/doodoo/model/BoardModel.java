package doodoo.model;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.RequestMapping;
import doodoo.dao.*;

@Controller
public class BoardModel {
	@RequestMapping("board/list.do")
	public String board_list(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		if(page==null)
			page="1";
		int curPage = Integer.parseInt(page);
		
		Map map = new HashMap();
		int start = (curPage*10)-9;
		int end = curPage*10;
		
		map.put("start", start);
		map.put("end", end);
		
		List<BoardVO> list = BoardDAO.boardListData(map);
		request.setAttribute("list", list);
		
		return "../board/list.jsp";
	}
}
