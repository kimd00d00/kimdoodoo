package dao;
import java.util.*;
import java.sql.*;
import vo.*;
import com.sist.conn.DBConnection;
public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	private DBConnection dbconn = DBConnection.newInstance();
	
	public List<CategoryBean> CategoryListData(){
		List<CategoryBean> list = new ArrayList<CategoryBean>();
		try {
			conn = dbconn.getConnection();
			String sql = "SELECT cno, title, poster "
					+"FROM food_category "
					+"ORDER BY cno ASC";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CategoryBean cb = new CategoryBean();
				cb.setCno(rs.getInt(1));
				cb.setTitle(rs.getString(2));
				cb.setPoster(rs.getString(3));
				list.add(cb);
			}
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			dbconn.disConnection(ps);
		}
		return list;
	}
}
