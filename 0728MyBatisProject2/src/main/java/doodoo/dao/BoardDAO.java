package doodoo.dao;

import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
public class BoardDAO {
	private static SqlSessionFactory ssf;
	static {
		//XML을 읽고 데이터를 주입시킨다.
		 try {
			 Reader reader = Resources.getResourceAsReader("Config.xml");
			 ssf = new SqlSessionFactoryBuilder().build(reader);
		 }catch(Exception ex) {ex.printStackTrace();}
	}
	
	//목록보기
	public static List<BoardVO> boardListData(Map map){
		//Map을 주고 #{start}와 #{end}에 값을 넣어줄 것임.
		SqlSession session = null; //SqlSession == Connection
		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			session = ssf.openSession();
			list = session.selectList("boardListData", map);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			session.close(); //반환
		}
		return list;
	}
	//상세보기
}
