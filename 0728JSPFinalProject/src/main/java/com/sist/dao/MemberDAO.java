package com.sist.dao;
import com.sist.vo.*;

import java.io.*;
import java.util.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MemberDAO {
	private static SqlSessionFactory ssf;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	//회원가입-아이디 중복체크
	public static int memberIdCheck(String id) {
		int count = 0;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			count = session.selectOne("memberIdCheck",id);
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("MemberDAO: memberIdCheck(String id) ERROR");
		}finally {
			if(session!=null)
				session.close();
		}
		return count;
	}
	//회원가입-이메일 중복체크
	public static int memberEmailCheck(String email) {
		int count = 0;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			count = session.selectOne("memberEmailCheck",email);
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("MemberDAO: memberEmailCheck(String email) ERROR");
		}finally {
			if(session!=null)
				session.close();
		}
		return count;
	}
	//회원가입-전화번호 중복체크
	public static int memberTelCheck(String tel) {
		int count = 0;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			count = session.selectOne("memberTelCheck",tel);
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("MemberDAO: memberTelCheck(String tel) ERROR");
		}finally {
			if(session!=null)
				session.close();
		}
		return count;
	}
	//회원가입 INSERT
	public static void memberInsert(MemberVO vo) {
		SqlSession session = null;
		try {
			session = ssf.openSession(true); //AutoCommit
			session.insert("memberInsert",vo);
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("MemberDAO: memberInsert(MemberVO vo) ERROR");
		}finally {
			if(session!=null)
				session.close();
		}
	}
	//
	public static MemberVO isLogin(String id, String pwd) {
		MemberVO vo = new MemberVO();
		SqlSession session = null;
		try {
			session = ssf.openSession();
			int count = session.selectOne("memberIdCount",id);
			if(count==0) {
				vo.setMsg("NOID");
			}else {
				vo = session.selectOne("memberInfoData",id);
				if(pwd.equals(vo.getPwd())) {
					vo.setMsg("OK");
				}else {
					vo.setMsg("NOPWD");
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return vo;
	}
}
