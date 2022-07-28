package doodoo.dao;

import java.util.List;

public class MainClass {

	public static void main(String[] args) {
		List<MyBoardVO> list = MyBoardDAO.boardListData();
		for(MyBoardVO vo:list) {
			System.out.println(vo.getNo()+"/"+vo.getName()+"/"+vo.getSubject()+"/"+vo.getContent());
		}
		
		int cnt = MyBoardDAO.boardCount();
		System.out.println(cnt+"개");
	}

}
