package dao;
import java.util.*; //List
import java.sql.*; //Connection, PreparedStatement, ResultSet
import javax.sql.*; //DataSource
import javax.naming.*; //Context: class객체 찾을 때 사용
public class ReplyBoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	//객체 주소 가지고 오기(Connection) -> 톰캣에 의해 10개 생성돼있음 -> POOL(java://comp/env)
	//객체명 : jdbc/oracle (우리가 이 이름으로 저장해 달라고 요청해놨음)
	public void getConnection() {
		try {
			Context init = new InitialContext(); // 이 안이 JNDI Java Naming Directory Interface
			Context c = (Context)init.lookup("java://comp/env");
			DataSource ds = (DataSource)c.lookup("jdbc/oracle");
			conn = ds.getConnection();
		}catch(Exception ex) {
			System.out.println("getConnection() Error!");
			ex.printStackTrace();
		}
	}
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {
			System.out.println("disConnection() Error!");
			ex.printStackTrace();
		}
	}
	
	public List<ReplyBoardVO> boardListData(int page){
		//페이지 나눠주는 기술 중요함
		List<ReplyBoardVO> list = new ArrayList<ReplyBoardVO>();
		try {
			getConnection();
			String sql = "SELECT no, subject, name, TO_CHAR(regdate,'YYYY-MM-DD'), hit, group_tab, num "
					+"FROM (SELECT no, subject, name, regdate, hit, group_tab, rownum as num "
					+"FROM (SELECT no, subject, name, regdate, hit, group_tab "
					+"FROM replyboard ORDER BY group_id DESC, group_step ASC)) "
					+"WHERE num BETWEEN ? AND ?";
			ps = conn.prepareStatement(sql);
			int rowSize = 10;
			int start = (rowSize*page)-(rowSize-1);
			int end = rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ReplyBoardVO vo = new ReplyBoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setHit(rs.getInt(5));
				vo.setGroup_tab(rs.getInt(6));
				
				list.add(vo);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
		return list;
	}
	
	public int boardRowCount() {
		int count=0;
		try {
			getConnection();
			String sql = "SELECT COUNT(*) FROM replyBoard";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
		return count;
	}
	
	public void replyInsert(ReplyBoardVO vo) {
		try {
			getConnection();
			conn.setAutoCommit(false);
			//상위 글의 정보(gi, gt, gs) => SELECT
			//gs의 순서 변경 => UPDATE
			//실제 저장 => INSERT
			//depth 변경 => UPDATE
			//이모든걸 일괄처리해주는게 트랜잭션.
			conn.commit();
		}catch(Exception ex) {
			try{
				conn.rollback();
			}catch(Exception ex2) {}
			ex.printStackTrace();
		}finally {
			try {
				conn.setAutoCommit(true);
			}catch(Exception ex) {}
			disConnection();
		}
	}
}
