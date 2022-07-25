package dao;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class LocationDAO {
	private Connection conn;
	private PreparedStatement ps;
	public void getConnection() {
		try {
			Context init = new InitialContext();
			Context c = (Context)init.lookup("java://comp/env");
			DataSource ds = (DataSource)c.lookup("jdbc/oracle");
			conn = ds.getConnection();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public List<LocationVO> locationListData(int page){
		List<LocationVO> list = new ArrayList<LocationVO>();
		try {
			getConnection();
			String sql = "SELECT no, title, poster, num "
					+"FROM (SELECT no,title,poster,rownum as num "
					+"FROM (SELECT no, title, poster "
					+"FROM seoul_location ORDER BY no ASC)) "
					+"WHERE num BETWEEN ? AND ?";
			ps = conn.prepareStatement(sql);
			int rowSize = 12;
//			int start = (page*12)-11;
//			int end = page*12; 이렇게 하는것보다 아래처럼 하는 게 더 유지보수에 좋음.
			int start = (page*rowSize)-(rowSize-1);
			int end = page*rowSize;
			
			ps.setInt(1,start);
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
			ex.printStackTrace();
		}finally {
			disConnection();
		}
		return list;
	}
	
	public LocationVO locationDetailData(int no) {
		LocationVO vo = new LocationVO();
		try {
			getConnection();
			String sql = "SELECT no, title, poster, msg, address "
					+"FROM seoul_location "
					+"WHERE no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setPoster(rs.getString(3));
			vo.setMsg(rs.getString(4));
			vo.setAddress(rs.getString(5));
			
			rs.close();
		}catch(Exception ex) {
			System.out.println("locationDetailData() Error");
			ex.printStackTrace();
		}finally {
			disConnection();
		}
		return vo;
	}
	
	public int locationTotalPage() {
		int total=0;
		try {
			getConnection();
			String sql = "SELECT CEIL(COUNT(*)/12.0) FROM seoul_location";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
			rs.close();
		}catch(Exception ex) {
			System.out.println("locationTotalPage() Error");
			ex.printStackTrace();
		}finally {
			disConnection();
		}
		return total;
	}
}
