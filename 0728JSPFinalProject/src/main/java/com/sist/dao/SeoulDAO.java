package com.sist.dao;


import java.io.*;
import java.util.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.*;

public class SeoulDAO {
	//1. XML파일 읽기
	private static SqlSessionFactory ssf;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			//경로를 src/main/java까지 자동으로 인식한다. 그러니까 src/main/java에 Config.xml을 두면 자동으로 인식 가능함!
			ssf = new SqlSessionFactoryBuilder().build(reader); //xml파일 파싱
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("SeoulDAO:getConnection() ERROR");
		}
	}
	
	public static List<SeoulLNSVO> seoulLNSListData(Map map){
		//ssf가 Connection 역할 SqlSession이 PreparedStatement역할 
		SqlSession session = null; 
		List<SeoulLNSVO> list = null;
		try {
			session = ssf.openSession();
			list = session.selectList("seoulLNSListData",map);
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("SeoulDAO:seoulLNSListData(Map map) EROOR");
		}finally {
			if(session != null)
				session.close();
		}
		return list;
	}
	
	public static int seoulLNSTotalPage(Map map){
		SqlSession session = null;
		int total = 0;
		try {
			session = ssf.openSession();
			total = session.selectOne("seoulLNSTotalPage",map);
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("SeoulDAO:seoulLNSTotalPage(Map map) ERROR");
		}finally {
			if(session!=null)
				session.close();
		}
		return total;
	}
	
	public static SeoulLNSVO seoulDetailData(Map map) {
		//seoulLNSDetailData
		SeoulLNSVO vo = new SeoulLNSVO();
		SqlSession session = null; //지역변수는 초기값이 꼭 있어야 한다
		try {
			session = ssf.openSession();
			vo = session.selectOne("seoulLNSDetailData",map);
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("SeoulDAO:seoulDetailData(Map map) ERROR");
		}finally {
			if(session!=null)
				session.close();
		}
		return vo;
	}
}
