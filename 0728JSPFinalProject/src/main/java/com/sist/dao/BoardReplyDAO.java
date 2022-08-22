package com.sist.dao;

import java.io.Reader;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.BoardReplyVO;

import controller.Controller;
import controller.RequestMapping;

public class BoardReplyDAO {
	private static SqlSessionFactory ssf;
	 static 
	   {
		   try
		   {
			   Reader reader=Resources.getResourceAsReader("Config.xml");
			   ssf=new SqlSessionFactoryBuilder().build(reader);
		   }catch(Exception ex)
		   {
			   ex.printStackTrace();
		   }
	   }
	 
	 public static List<BoardReplyVO> boardReplyListData(Map map){
		 SqlSession session = null;
		 List<BoardReplyVO> list = null;
		 try {
			 session = ssf.openSession();
			 list = session.selectList("boardReplyListData", map);
		 }catch(Exception ex) {
			 ex.printStackTrace();
		 }finally {
			 if(session!=null)
				 session.close();
		 }
		 return list;
	 }
	 public static int boardReplyTotalPage(){
		 SqlSession session = null;
		 int total = 0;
		 try {
			 session = ssf.openSession();
			 total = session.selectOne("boardReplyTotalPage");
		 }catch(Exception ex) {
			 ex.printStackTrace();
		 }finally {
			 if(session!=null)
				 session.close();
		 }
		 return total;
	 }
	 
	 public static void boardReplyInsert(BoardReplyVO vo) {
		 SqlSession session = null;
		 try {
			 session = ssf.openSession(true);
			 session.insert("boardReplyInsert",vo);
		 }catch(Exception ex) {
			 ex.printStackTrace();
		 }finally {
			 if(session!=null)
				 session.close();
		 }
	 }
	 
	 public static List<BoardReplyVO> boardReplyAdminData(){
		 SqlSession session = null;
		 List<BoardReplyVO> list = null;
		 try {
			 session = ssf.openSession();
			 list = session.selectList("boardReplyAdminData");
		 }catch(Exception ex) {
			 
		 }finally {
			 if(session!=null)
				 session.close();
		 }
		 return list;
	 }
	 
	 public static BoardReplyVO boardReplyDetailData(int no){
		 SqlSession session = null;
		 BoardReplyVO vo = null;
		 try {
			 session = ssf.openSession();
			 session.update("boardReplyHitIncrement",no);
			 session.commit();
			 vo = session.selectOne("boardReplyDetailData",no);
		 }catch(Exception ex) {
			 
		 }finally {
			 if(session!=null)
				 session.close();
		 }
		 return vo;
	 }
	 
	 public static void boardReplyInsertOk(int pno, BoardReplyVO vo) {
		 SqlSession session = null;
		 try {
			 session = ssf.openSession();
			 int gi = session.selectOne("boardReplyInfoData",pno);//group_id 가져오기
			 vo.setGroup_id(gi);
			 session.insert("boardReplyInsertOk",vo); //답변 insert
			 session.update("boardReplyIsReply",pno); //질문글 isReply 업데이트
			 session.commit();
		 }catch(Exception ex) {
			 session.rollback();
			 ex.printStackTrace();
		 }finally {
			 if(session!=null)
				 session.close();
		 }
	 }
	 
	 public static BoardReplyVO boardReplyUpdateData(int no){
		 SqlSession session = null;
		 BoardReplyVO vo = null;
		 try {
			 session = ssf.openSession();
			 vo = session.selectOne("boardReplyDetailData",no);
		 }catch(Exception ex) {
			 
		 }finally {
			 if(session!=null)
				 session.close();
		 }
		 return vo;
	 }
	 
	 public static void boardReplyUpdate(BoardReplyVO vo) {
		 SqlSession session = null;
		 try {
			 session = ssf.openSession();
			 session.update("boardReplyUpdate",vo);
			 session.commit();
		 }catch(Exception ex) {
			 ex.printStackTrace();
		 }finally {
			 if(session!=null)
				 session.close();
		 }
	 }
	 
	 public static void boardDelete(int no) {
		 SqlSession session = null;
		 try {
			 session = ssf.openSession(true);
			 int gi = session.selectOne("boardGetGroupId",no);
			 session.delete("boardReplyDelete",gi);
		 }catch(Exception ex) {
			 ex.printStackTrace();
		 }finally {
			 if(session!=null)
				 session.close();
		 }
	 }
}
