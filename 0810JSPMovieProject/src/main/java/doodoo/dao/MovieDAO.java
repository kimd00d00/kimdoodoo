package doodoo.dao;

import java.sql.*;
import java.util.*;
public class MovieDAO {
	private Connection conn; //연결 객체
	private PreparedStatement ps; //전송 객체
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE"; //jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC
	
	//드라이버 등록
	public MovieDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex){}
	}
	
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL,"hr","happy");
		}catch(Exception ex) {}
	}
	
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {}
	}
	
	public List<MovieVO> movieListData(int cno, int page){
		List<MovieVO> list = new ArrayList<MovieVO>();
		try {
			getConnection();
			String sql = "SELECT mno, cno, poster, title, num "
					+ "FROM (SELECT mno, cno, poster, title, rownum as num "
					+ "FROM (SELECT mno, cno, poster, title "
					+ "FROM project_movie WHERE cno=? ORDER BY mno ASC)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps = conn.prepareStatement(sql);
//			int rowSize = 12;
//			int start = (page*rowSize)-(rowSize-1);
//			int end = page*rowSize;
			ps.setInt(1, cno);
			ps.setInt(2, (page*12)-11);
			ps.setInt(3, page*12);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				MovieVO vo = new MovieVO();
				vo.setMno(rs.getInt(1));
				vo.setCno(rs.getInt(2));
				vo.setPoster(rs.getString(3));
				vo.setTitle(rs.getString(4));
				
				list.add(vo);
			}
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
		return list;
	}
	
	public int movieTotalPage(int cno) {
		int total = 0;
		try {
			getConnection();
			String sql = "SELECT CEIL(COUNT(*)/12.0) FROM project_movie WHERE cno=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cno);
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
	
	public MovieVO movieDetailData(int mno) {
		MovieVO vo = new MovieVO();
		try {
			getConnection();
			String sql = "SELECT mno, cno, title, grade, reserve, genre, time, regdate, director, actor, "
					+ "showuser, poster, key, score "
					+ "FROM project_movie WHERE mno=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, mno);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setMno(rs.getInt(1));
			vo.setCno(rs.getInt(2));
			vo.setTitle(rs.getString(3));
			vo.setGrade(rs.getString(4));
			vo.setReserve(rs.getString(5));
			vo.setGenre(rs.getString(6));
			vo.setTime(rs.getString(7));
			vo.setRegdate(rs.getString(8));
			vo.setDirector(rs.getString(9));
			vo.setActor(rs.getString(10));
			vo.setShowuser(rs.getString(11));
			vo.setPoster(rs.getString(12));
			vo.setKey(rs.getString(13));
			vo.setScore(rs.getDouble(14));
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
		return vo;
	}
}
