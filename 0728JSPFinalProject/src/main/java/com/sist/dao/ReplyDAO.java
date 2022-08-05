package com.sist.dao;

import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.*;

public class ReplyDAO {
	private static SqlSessionFactory ssf;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static List<ReplyVO> replyListData(ReplyVO vo)
	   {
		   List<ReplyVO> list=null;
		   SqlSession session=null;
		   try
		   {
			   session=ssf.openSession();
			   list=session.selectList("replyListData",vo);
		   }catch(Exception ex)
		   {
			   ex.printStackTrace();
			   System.out.println("ReplyDAO: replyListData ERROR");
		   }
		   finally
		   {
			   if(session!=null)
				   session.close(); // 반환 ==> POOLED(DBCP) => Connection생성(8개)
		   }
		   return list;
	   }
	public static void replyInsert(ReplyVO vo){
		SqlSession session = null;
		try {
			session = ssf.openSession(); //openSession(true) 하면 AutoCommit
			session.update("countIncrement",vo);
			session.insert("replyInsert",vo); //AutoCommit 아님
			session.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("ReplyDAO: replyInsert ERROR");
		}finally {
			if(session!=null)
				session.close();
		}
//		SqlSession session = ssf.openSession(true);
//		session.insert("replyInsert",vo);
//		session.close();
	}
	public static void replyDelete(int no) {
		SqlSession session = null;
		try {
			session = ssf.openSession();
			session.delete("replyDelete",no);
			session.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
	}
	public static void replyUpdate(ReplyVO vo) {
		SqlSession session = null;
		try {
			session = ssf.openSession();
			session.delete("replyUpdate",vo);
			session.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
	}
}