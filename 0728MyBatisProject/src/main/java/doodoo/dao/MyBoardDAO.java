package doodoo.dao;

import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
public class MyBoardDAO {
	private static SqlSessionFactory ssf; //XML 읽는 역할
	static {
		try {
			//XML Parsing
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static List<MyBoardVO> boardListData(){
		return ssf.openSession().selectList("boardListData");//mapper에서 작성한 select 쿼리의 id
	}

	public static int boardCount(){
		return ssf.openSession().selectOne("boardCount");//값을 하나만 받을 때는 selectOne
	}
}
