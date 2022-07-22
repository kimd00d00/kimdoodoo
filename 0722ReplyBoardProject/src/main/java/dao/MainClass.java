package dao;
import java.io.*;
public class MainClass {

	public static void main(String[] args) {
		try {
			String data = "";
//			String data1 = new;
			FileInputStream fis = new FileInputStream("c:\\webDev\\datatable\\food_location.sql");
			int i=0;
			while((i=fis.read())!=-1) {
				data += String.valueOf((char)i);
//				data1.append((char)i);
				System.out.println(data);
			}
			fis.close();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
