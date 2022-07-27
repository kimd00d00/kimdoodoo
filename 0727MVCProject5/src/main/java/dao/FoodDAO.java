package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	public void getConnection() {
		try {
			Context init = new InitialContext(); //탐색기를 열고
			Context c = (Context)init.lookup("java://comp/env"); //Connection이 저장돼 있는 곳으로!
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
			System.out.println("disConnection() Error");
			ex.printStackTrace();
		}
	}
	
	public List<CategoryVO> categoryListData(int no){
		List<CategoryVO> list = new ArrayList<CategoryVO>();
		try {
			int start = 0, end = 0;
			if(no==1) { //믿고 보는 맛집 리스트
				start = 1;
				end = 12;
			}
			if(no==2) { //지역별 인기 맛집
				start = 13;
				end = 18;
			}
			if(no==3) { //메뉴별 인기 맛집
				start = 19;
				end = 30;
			}
			getConnection();
			String sql = "SELECT cno, title, subject, poster "
					+"FROM food_category "
					+"WHERE cno BETWEEN ? AND ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CategoryVO vo = new CategoryVO();
				vo.setCno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setSubject(rs.getString(3));
				vo.setPoster(rs.getString(4));
				list.add(vo);
			}
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection(); //재사용을 위한 반환
		}
		return list;
	}
}
