package controller;

import java.lang.reflect.Method;
import java.util.Scanner;

class Model{
	
	@RequestMapping("list.do")
	public void aaa() {
		System.out.println("게시물 목록");
	}
	@RequestMapping("find.do")
	public void bbb() {
		System.out.println("게시물 찾기");
	}
	@RequestMapping("insert.do")
	public void ccc() {
		System.out.println("게시물 추가");
	}
}

public class MainClass {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("URL:");
		String url = scan.next(); //list.do 입력
		try {
			Class clsName = Class.forName("controller.Model"); //클래스 정보 읽기
			//Class.forName : 클래스가 갖고 있는 모든 정보를 가지고 온다.
			//멤버 변수, 메서드 호출, 메모리 할당
			Object obj = clsName.getDeclaredConstructor().newInstance();
			//new Model()
			
			//선언된 메서드 호출
			Method[] methods = clsName.getDeclaredMethods();
			for(Method m:methods) {
				RequestMapping rm = m.getAnnotation(RequestMapping.class);
				if(url.equals(rm.value())) {
					m.invoke(obj, null); //invoke 
					//어노테이션만 있으면 메서드를 다 찾을 수 있다.
				}
				System.out.println(m.getName());
			}
		}catch(Exception ex){}
	}
}
