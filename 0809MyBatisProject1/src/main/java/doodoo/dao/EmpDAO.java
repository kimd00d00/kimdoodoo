package doodoo.dao;

import java.io.Reader;
import java.util.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class EmpDAO {
/*
 * <select id="empDeptJoinData" resultType="doodoo.dao.EmpVO">
		SELECT empno, ename, job, hiredate, sal, deptno, dname, loc
		FROM emp, dept
		WHERE emp.deptno == dept.deptno
	</select>
 */
	private static SqlSessionFactory ssf;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static List<EmpVO> empDeptJoinData(){
		SqlSession session = ssf.openSession();
		List<EmpVO> list = session.selectList("empDeptJoinData");
		session.close();
		return list;
	}
	
	public static EmpVO empDeptDetailData(int empno) {
		SqlSession session = ssf.openSession();
		EmpVO vo = session.selectOne("empDeptDetailData", empno);
		session.close();
		return vo;
	}
	
	public static List<String> empGetEnameData(){
		SqlSession session = ssf.openSession();
		List<String> list = session.selectList("empGetEnameData");
		session.close();
		return list;
	}
	
	public static List<EmpVO> empFindData(Map map){
		SqlSession session = ssf.openSession();
		List<EmpVO> list = session.selectList("empFindData",map);
		session.close();
		return list;
	}
	
}
