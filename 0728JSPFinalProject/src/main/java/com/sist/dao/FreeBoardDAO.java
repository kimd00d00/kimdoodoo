package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import com.sist.vo.*;
public class FreeBoardDAO {
	private static SqlSessionFactory ssf;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static List<FreeBoardVO> boardListData(Map map)
	   {
		   List<FreeBoardVO> list=null;
		   SqlSession session=null;
		   try
		   {
			   session=ssf.openSession();
			   list=session.selectList("boardListData",map);
		   }catch(Exception ex)
		   {
			   ex.printStackTrace();
		   }
		   finally
		   {
			   if(session!=null)
				   session.close(); // 반환 ==> POOLED(DBCP) => Connection생성(8개)
		   }
		   return list;
	   }
	
	public static int boardTotalPage(){
		SqlSession session = null;
		int total = 0;
		try {
			session = ssf.openSession();
			total = session.selectOne("boardTotalPage");
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("FreeBoardDAO: boardTotalPage ERROR");
		}finally {
			if(session!=null)
				session.close();
		}
		return total;
	}
	public static void boardInsert(FreeBoardVO vo){
		SqlSession session = null;
		try {
			session = ssf.openSession(); //openSession(true) 하면 AutoCommit
			session.insert("boardInsert",vo); //AutoCommit 아님
			session.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("FreeBoardDAO: boardInsert ERROR");
		}finally {
			if(session!=null)
				session.close();
		}
	}
	
	public static FreeBoardVO boardDetailData(int no) {
		SqlSession session = null;
		FreeBoardVO vo = new FreeBoardVO();
		try {
			session = ssf.openSession(); //==setAutoCommit(false)와 같음.
			session.update("hitIncrement",no);//조회수 증가
			session.commit();
			vo = session.selectOne("boardDetailData",no);
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("FreeBoardDAO: boardDetailData ERROR");
//			session.rollback();//에러나면 롤백해도되고
		}finally {
			if(session!=null)
				session.close();
		}
		return vo;
	}
	public static FreeBoardVO boardUpdateData(int no) {
		SqlSession session = null;
		FreeBoardVO vo = new FreeBoardVO();
		try {
			session = ssf.openSession(); //==setAutoCommit(false)와 같음.
			vo = session.selectOne("boardDetailData",no);
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("FreeBoardDAO: boardUpdateData ERROR");
		}finally {
			if(session!=null)
				session.close();
		}
		return vo;
	}
	
	public static String boardPwdCheck(int no, String pwd) {
		String result="";
		SqlSession session = null;
		try {
			session = ssf.openSession();
			String db_pwd = session.selectOne("boardGetPwd",no);
			System.out.println("원래 비밀번호:"+db_pwd);
			System.out.println("입력한 비밀번호:"+pwd);
			if(db_pwd.equals(pwd)) {
				result="yes";
			}else {
				result="no";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		System.out.println("result:"+result);
		return result;
	}
	
	public static void boardUpdate(FreeBoardVO vo){
		SqlSession session = null;
		try {
			session = ssf.openSession(true); //openSession(true) 하면 AutoCommit
			session.insert("boardUpdate",vo); //AutoCommit 아님
//			session.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("FreeBoardDAO: boardUpdate ERROR");
		}finally {
			if(session!=null)
				session.close();
		}
	}
	
	public static String boardDelete(int no, String pwd) {
		String result="";
		SqlSession session = null;
		try {
			session = ssf.openSession();
			String db_pwd = session.selectOne("boardGetPwd",no);
			if(db_pwd.equals(pwd)) {
				result="yes";
				session.delete("boardDelete",no);
				session.commit();
			}else {
				result="no";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("FreeBoardDAO: boardDelete() ERROR");
		}finally {
			if(session!=null)
				session.close();
		}
		return result;
	}
}
