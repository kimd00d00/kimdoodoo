package dao;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class LocationDAO {
	private Connection conn;
	private PreparedStatement ps;
	//DBCP -> POOL(Connection을 저장해서 모아 놓고 관리하는 것)
	//연결 객체 가지고 오기
	public void getConnection() {
		try {
			//저장된 위치를 가지고 온다.(JNDI)-> java://comp/env에 Connection 객체가 저장된다.
			//1. 탐색기를 연다.
			Context init = new InitialContext();
			//2. 드라이버 열기 (lookup = 이름으로 객체를 찾아 오는 것)
			Context c = (Context)init.lookup("java://comp/env");
			//3. 저장된 폴더에서 Connection을 읽어 온다.
			DataSource ds = (DataSource)c.lookup("jdbc/oracle");
			conn = ds.getConnection();
		}catch(Exception ex) {
			
		}
	}
	//다 쓰고 나서 반환하기(닫기가 아님!!)
	//-> commons-dbcp.jar에서 지원해준다. 다른 사용자가 재사용할 수 있게 해줌.
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	//기능 구현
	public List<LocationVO> locationListData(int page){
		List<LocationVO> list = new ArrayList<LocationVO>();
		try {
			getConnection(); //미리 생성된 Connection 객체 주소를 얻어 온다
			String sql = "SELECT no, title, poster, num "
					+ "FROM (SELECT no, title, poster, rownum as num "
					+ "FROM (SELECT no, title, poster "
					+ "FROM seoul_location ORDER BY no ASC)) "
					+ "WHERE num BETWEEN ? AND ?";
			//rownum을 이용하면 Top-N만 가능하고 중간에서 잘라올 수 없다.
			ps = conn.prepareStatement(sql);
			int rowSize = 12;
			int start = (rowSize*page)-(rowSize-1);
			int end = rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				LocationVO vo = new LocationVO();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
				list.add(vo);
			}
		}catch(Exception ex) {
			
		}finally {
			disConnection();
		}
		return list;
	}
	
	//총 페이지 구하기
	public int locationTotalPage() {
		int total = 0;
		try {
			getConnection();
			String sql = "SELECT CEIL(COUNT(*)/12.0) "
					+"FROM seoul_location";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
		return total;
	}
}
