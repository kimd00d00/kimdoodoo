package dao;
/*
 * Bean -> 데이터를 모아서 한번에 전송/출력하는 것
 * 		VO:Value Object(Spring), DTO:Data Transfer Object(MyBatis), Entity(JPA)
 * 		만든 업체에 따라 데이터 모으는 방법은 같지만 이름만 다르다.
 * 		읽기/쓰기를 위해 getter/setter
 * 		데이터베이스 컬럼 매칭이 중요하다.
 * ------아래와 같은 테이블이 있다고 해 보자.
 * 	name    읽기/쓰기
 *  address 읽기/쓰기
 *  tel     읽기/쓰기
 *  sex     읽기/쓰기
 *  age     읽기/쓰기
 *  content 읽기
 *  
 *  모든 컬럼이 다 읽기와 쓰기를 다 지원하는 것은 아니고, 읽기 또는 쓰기 하나만 지원할 수도 있다.
 *  그런 특성에 따라 getter와 setter 메서드를 생성해 준다.
 *  
 *  데이터를 받아서 / 전송하고 / 출력 -> JSP
 *  
 */
public class MemberBean {
	private String name, address, tel, sex;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
