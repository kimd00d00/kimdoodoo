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
			ex.printStackTrace();
			System.out.println("getConnection() Error!");
		}
	}
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("disConnection() Error!");
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
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("boardListData() Error!");
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
			System.out.println("boardRowCount() Error!");
		}finally {
			disConnection();
		}
		return count;
	}
	
	public void boardInsert(ReplyBoardVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO replyBoard(no, name, subject, content, pwd, group_id)"
					+ " VALUES((SELECT NVL(MAX(no)+1,1) FROM replyBoard),?,?,?,?, "
					+ "(SELECT NVL(MAX(group_id)+1,1) FROM replyboard))";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			
			ps.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("DAO:boardInsert() Error!");
		}finally {
			disConnection();
		}
	}
	
	public void replyInsert(int pno, ReplyBoardVO vo) {
		try {
			getConnection();
			conn.setAutoCommit(false); //트랜잭션 -> 일단 오토커밋 끔
			//상위 글의 정보(gi, gt, gs) => SELECT
			String sql = "SELECT group_id, group_step, group_tab "
					+ "FROM replyBoard "
					+ "WHERE no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pno);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int gi = rs.getInt(1);
			int gs = rs.getInt(2);
			int gt = rs.getInt(3);
			rs.close();
			//*****매우중요***** gs의 순서 변경 => UPDATE
			sql = "UPDATE replyboard SET "
					+ "group_step=group_step+1 "
					+ "WHERE group_id=? AND group_step>?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, gi);
			ps.setInt(2, gs);
			ps.executeUpdate();
			//실제 저장 => INSERT
			sql = "INSERT INTO replyBoard(no,name,subject,content,pwd,group_id, group_step, group_tab, root) "
					+ "VALUES((SELECT NVL(MAX(no)+1,1) FROM replyBoard), ?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.setInt(5, gi); //group_id
			ps.setInt(6, gs+1); //group_step
			ps.setInt(7, gt+1); //group_tab
			ps.setInt(8, pno); //원글 번호
			ps.executeUpdate();
			//depth 변경 => UPDATE
			
			sql = "UPDATE replyBoard SET "
					+ "depth=depth+1 "
					+ "WHERE no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pno);
			ps.executeUpdate();
			/* 이모든걸 일괄처리해주는게 트랜잭션.
			 * UPDATE나 DELETE나 그런 게 한 번에 여러 종류 실행되어야 하면 쓰는거임.
			 * SELECT 여러 번 한다고 쓰는 게 아님. 데이터에 변경사항이 생기는 상황(DML)에서..
			 * 그리고 중간에 뭐라도 실패하면 롤백도 해줘야함 */
			
			conn.commit();
		}catch(Exception ex) {
			try{
				conn.rollback();
			}catch(Exception ex2) {
				ex2.printStackTrace();
				System.out.println("rollback() Error!");
			}
			ex.printStackTrace();
			System.out.println("commit() Error!");
		}finally {
			try {
				conn.setAutoCommit(true);
			}catch(Exception ex) {}
			disConnection();
		}
	}
	
	public ReplyBoardVO boardDetail(int no) {
		ReplyBoardVO vo = new ReplyBoardVO();
		
		try {
			getConnection();
			//조회수 증가
			String sql = "UPDATE replyBoard SET "
					+ "hit = hit+1 "
					+ "WHERE no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
			
			//왜 굳이 비밀번호도 불러옴? -> 비밀번호 맞으면 수정가능하게 해주려고.
			sql = "SELECT no, name, subject, content, regdate, hit, pwd "
				+"FROM replyBoard "
				+"WHERE no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setRegdate(rs.getDate(5));
			vo.setHit(rs.getInt(6));
			vo.setPwd(rs.getString(7));
			
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("DAO:boardDetail() Error!");
		}finally {
			disConnection();
		}
		
		return vo;
		//이걸 이제 Model로 보내 Model이 JSP로 보내게끔 해줄것
	}
	
	public ReplyBoardVO boardUpdateData(int no) {
		ReplyBoardVO vo = new ReplyBoardVO();
		try {
			getConnection();
			//왜 굳이 비밀번호도 불러옴? -> 비밀번호 맞으면 수정가능하게 해주려고.
			String sql = "SELECT no, name, subject, content"
				+"FROM replyBoard "
				+"WHERE no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("DAO:boardDetail() Error!");
		}finally {
			disConnection();
		}
		
		return vo;
		//이걸 이제 Model로 보내 Model이 JSP로 보내게끔 해줄것
	}
	
	public boolean boardUpdate(ReplyBoardVO vo) {
		boolean bCheck = false;
		try {
			getConnection();
			String sql = "SELECT pwd FROM replyBoard "
					+ "WHERE no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getNo());
			ResultSet rs = ps.executeQuery();
			rs.next();
			String db_pwd = rs.getString(1);
			rs.close();
			
			if(db_pwd.equals(vo.getPwd())) {
				bCheck = true;
				//비밀번호 일치 -> 본인확인OK -> 수정처리 할 것임
				sql = "UPDATE replyBoard SET "
						+ "name=?, subject=?, content=? "
						+ "WHERE no=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, vo.getName());
				ps.setString(2, vo.getSubject());
				ps.setString(3, vo.getContent());
				ps.setInt(4, vo.getNo());
				
				ps.executeUpdate();
			}else {
				bCheck = false;
				//수정 안되게 할 것임
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
		return bCheck;
	}
	
	//삭제하기
	public String boardDelete(int no, String pwd) {
		String result = "";
		try {
			getConnection();
			conn.setAutoCommit(false);
			//비밀번호 확인
			String sql = "SELECT pwd FROM replyBoard "
					+ "WHERE no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String db_pwd = rs.getString(1);
			rs.close();
			if(db_pwd.equals(pwd)) {
				result = "yes";
				sql = "SELECT root, depth FROM replyBoard "
						+ "WHERE no=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, no);
				rs = ps.executeQuery();
				rs.next();
				int root = rs.getInt(1);
				int depth = rs.getInt(2);
				rs.close();
				
				if(depth>0) { //답글이 있는 상태
					sql = "UPDATE replyBoard SET "
							+ "subject='관리자가 삭제한 글입니다.', "
							+ "content='관리자가 삭제한 글입니다.' "
							+ "WHERE no=?";
					ps = conn.prepareStatement(sql);
					ps.setInt(1, no);
					ps.executeUpdate();
				}else { //답글 없음
					sql = "DELETE FROM replyBoard "
							+"WHERE no=?";
					ps = conn.prepareStatement(sql);
					ps.setInt(1, no);
					ps.executeUpdate();
				}
				sql = "UPDATE replyBoard SET "
						+ "depth=depth-1 "
						+ "WHERE no=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, root);
				ps.executeUpdate();
			}else {
				result = "no";
			}
			conn.commit();
		}catch(Exception ex) {
			try{
				System.out.println("DAO:boardDelete():commit Error!");
				conn.rollback();
			}catch(Exception ex2) {
				ex.printStackTrace();
				System.out.println("DAO:boardDelete():rollback Error!");
			}
			ex.printStackTrace();
			System.out.println("DAO:boardDelete() Error!");
		}finally {
			try {
				conn.setAutoCommit(true);
			}catch(Exception ex) {}
			disConnection();
		}
		return result;
	}
}
