package doodoo.dao;

import java.util.*;
import java.sql.*;

public class EmpDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	//드라이버 등록
	public EmpDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//리플렉션 : 클래스 정보를 읽어와서 변수를 초기화하고 메서드를 호출하는 것
			//변수 초기화 = Setter DI / 메서드 호출 = Method DI ***DI에 대한 개념 매우 중요함
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	//오라클 연결
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL,"hr","happy");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	//오라클 닫기
	public void disConnection() {
		try {
			if(ps!=null)
				ps.close();
			if(conn!=null)
				conn.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	//기능 처리
	public List<EmpVO> empListData(){
		List<EmpVO> list = new ArrayList<EmpVO>();
		
		try {
			getConnection();
			String sql = "SELECT empno, ename, job, mgr, hiredate, sal, comm, deptno "
					+ "FROM emp";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				EmpVO vo = new EmpVO();
				vo.setEmpno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setMgr(rs.getInt(4));
				vo.setHiredate(rs.getDate(5));
				vo.setSal(rs.getInt(6));
				vo.setComm(rs.getInt(7));
				vo.setDeptno(rs.getInt(8));
				list.add(vo);
			}
		}catch(Exception ex) {
			
		}finally {
			disConnection();
		}
		
		return list;
	}
	/*----------------------------------------------------------------- => mapper.xml의 역할*/
}
