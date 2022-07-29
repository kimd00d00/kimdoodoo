package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.FoodCategoryVO;
import com.sist.vo.FoodVO;

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
}
