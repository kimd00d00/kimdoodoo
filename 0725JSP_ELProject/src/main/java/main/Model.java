package main;
import javax.servlet.http.HttpServletRequest;

public class Model {
	public void getEmpData(HttpServletRequest request) {
		Employee e = new Employee();
		e.setEno(1100);
		e.setName("kimdoodoo");
		e.setDept("dev");
		
		request.setAttribute("e", e);
	}
}
