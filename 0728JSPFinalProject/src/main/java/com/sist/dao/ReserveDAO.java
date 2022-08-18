package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.FoodVO;

import java.io.*;

public class ReserveDAO {
	private static SqlSessionFactory ssf;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public static List<FoodVO> reserveFoodData(String type){
		List<FoodVO> list = null;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			list = session.selectList("reserveFoodData",type);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	//예약일 읽기
	public static String reserveGetDate(int fno) {
		String rday = "";
		SqlSession session = null;
		try {
			session = ssf.openSession();
			rday = session.selectOne("reserveGetDate",fno);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return rday;
	}
	//예약시간 읽기
	public static String reserveGetTime(int rno) {
		String rtime = "";
		SqlSession session = null;
		try {
			session = ssf.openSession();
			rtime = session.selectOne("reserveGetTime",rno);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return rtime;
	}
	public static String reserveRealTime(int tno) {
		String realtime = "";
		SqlSession session = null;
		try {
			session = ssf.openSession();
			realtime = session.selectOne("reserveRealTime",tno);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return realtime;
	}
}
