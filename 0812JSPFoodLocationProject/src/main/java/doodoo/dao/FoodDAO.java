package doodoo.dao;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class FoodDAO {
	private static SqlSessionFactory ssf;
	static{
		   try{
			   Reader reader=Resources.getResourceAsReader("Config.xml");
			   ssf=new SqlSessionFactoryBuilder().build(reader);
		   }catch(Exception ex){
			  ex.printStackTrace();
		   }
	}
	public static List<FoodVO> foodFindData(String address){
		SqlSession session = ssf.openSession();
		List<FoodVO> list = session.selectList("foodFindData",address);
		session.close();
		return list;
	}
}
