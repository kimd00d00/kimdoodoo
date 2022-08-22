package dao;
import java.util.*;
import java.sql.*;

import com.sist.conn.DBConnection;
public class MemberDAO {
	private Connection conn;
	private PreparedStatement ps;
	private DBConnection dbconn = DBConnection.newInstance();
	public String isLogin(String id, String pwd) {
		//로그인 경우의 수는 3개임 -> 그래서 Boolean으로 못 보내고 있음.
		//ID없는 경우, ID있는데 비밀번호 틀린 경우, 로그인 성공
		//String으로 넘겨도 되고 숫자로 넘겨도 되는데 숫자는 대체로 "상수화"해서 넘긴다. LOGIN = 100 뭐 이런식 => 가독성때문에요
		String result = "";
		try {
			conn = dbconn.getConnection();
			//우선 id 체크
			String sql = "SELECT COUNT(*) FROM jspMember "
					+"WHERE id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			rs.close();
			if(count==0) {
				//ID 없음
				result = "NOID";
			}
			if(count!=0) {
				//ID 있음 -> PW검색시작
				sql = "SELECT pwd, name FROM jspMember "
						+"WHERE id=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, id);
				rs = ps.executeQuery();
				rs.next();
				String db_pwd = rs.getString(1);
				String name = rs.getString(2);
				rs.close();
				if(db_pwd.equals(pwd)) {
					result = name;
				}else {
					result = "NOPWD";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Error! : isLogin()");
		}finally {
			dbconn.disConnection(ps);
		}
		return result;
	}
}
