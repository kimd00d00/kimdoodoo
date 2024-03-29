package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.*;

import java.io.*;

public class FoodDAO {
	//XML 파싱! 등록된 데이터를 읽어 올 것이다.
	private static SqlSessionFactory ssf;
	static { //자동으로 수행하도록 함 == XML에서 오류나면 바로 오류남.
		try {
			//XML 읽기
			Reader reader = Resources.getResourceAsReader("Config.xml");
			//Config에 mapper를 등록해 놓고 Config를 읽으면 자동으로 다 읽어온다.
			ssf = new SqlSessionFactoryBuilder().build(reader);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//mapper에 등록한 id와 메서드명이 다르면 에러가 난다.
	public static List<FoodCategoryVO> foodCategoryData(){
		SqlSession session = null;
		List<FoodCategoryVO> list = null;
		try {//try-catch 가 필수는 아니지만 에러 방지를 위해 권장된다.
			session = ssf.openSession(); //connection
			list = session.selectList("foodCategoryData");
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}
	

	public static List<FoodVO> foodListData(int cno){
		SqlSession session = null;
		List<FoodVO> list = null;
		try {
			session = ssf.openSession();
			list = session.selectList("foodListData",cno);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}

	public static FoodCategoryVO foodCategoryInfoData(int cno) {
		SqlSession session = null;
		FoodCategoryVO vo = null;
		try {
			session = ssf.openSession();
			vo = session.selectOne("foodCategoryInfoData",cno);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return vo;
	}
	
	public static FoodVO foodDetailData(int fno) {
		SqlSession session = null;
		FoodVO vo = null;
		try {
			session = ssf.openSession();
			vo = session.selectOne("foodDetailData",fno);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return vo;
	}
	
	public static List<FoodVO> foodLocationFindData(Map map){
		List<FoodVO> list = null;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			list = session.selectList("foodLocationFindData",map);
		}catch(Exception ex) {
			
		}finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	public static int foodLocationFindTotalPage(String address) {
		int total = 0;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			total = session.selectOne("foodLocationFindTotalPage",address);
		}catch(Exception ex) {
			
		}finally {
			if(session!=null)
				session.close();
		}
		return total;
	}
	
	public static int foodJjimCount(JjimVO vo) {
		int count = 0;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			count = session.selectOne("foodJjimCount",vo);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return count;
	}
	
	public static void foodJjimInsert(JjimVO vo) {
		SqlSession session = null;
		try {
			session = ssf.openSession();
			session.insert("foodJjimInsert",vo);
			session.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
	}
	
	public static FoodVO foodJjimListData(int fno){
		FoodVO vo = null;
		SqlSession session = ssf.openSession();
		try {
			session = ssf.openSession();
			vo = session.selectOne("foodJjimListData",fno);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return vo;
	}
	
	public static List<Integer> foodJjimGetFno(String id) {
		List<Integer> list = null;
		SqlSession session = ssf.openSession();
		try {
			session = ssf.openSession();
			list = session.selectList("foodJjimGetFno",id);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	public static void foodJjimDelete(JjimVO vo) {
		SqlSession session = null;
		try {
			session = ssf.openSession();
			session.insert("foodJjimDelete",vo);
			session.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
	}
	public static List<RecipeVO> foodRecipeMakeData(String type){
		List<RecipeVO> list = null;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			list = session.selectList("foodRecipeMakeData",type);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
}
