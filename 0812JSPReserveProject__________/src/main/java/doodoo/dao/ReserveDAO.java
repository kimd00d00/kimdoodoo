package doodoo.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
public class ReserveDAO {
	//XML 파싱->XML에 등록된 데이터를 읽어오자.
	private static SqlSessionFactory ssf;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public static List<FoodVO> foodListData(String type){
		SqlSession session = ssf.openSession();
		List<FoodVO> list = session.selectList("foodListData",type);
		session.close();
		return list;
	}
}
