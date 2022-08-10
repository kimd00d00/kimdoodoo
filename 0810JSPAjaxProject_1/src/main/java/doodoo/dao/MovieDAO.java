package doodoo.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;

public class MovieDAO {
	private static SqlSessionFactory ssf;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");//xml을 읽고 파싱해서 mapper 등을 등록해줄 것임
			ssf = new SqlSessionFactoryBuilder().build(reader);//파싱
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("connection ERROR");
		}
	}
	
	public static List<MovieVO> movieListData(Map map){
		SqlSession session = ssf.openSession();
		List<MovieVO> list = session.selectList("movieListData",map);
		session.close();
		return list;
	}
	
	public static MovieVO movieDetailData(int mno) {
		SqlSession session = ssf.openSession();
		MovieVO vo = session.selectOne("movieDetailData",mno);
		session.close();
		return vo;
	}
	
	public static int movieTotalPage(int cno) {
		SqlSession session = ssf.openSession();
		int total = session.selectOne("movieTotalPage",cno);
		session.close();
		return total;
	}
}
