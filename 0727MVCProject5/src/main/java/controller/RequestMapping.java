package controller;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
//@ReuqestMapping("list.do");
@Retention(RUNTIME)
@Target(METHOD)
//메서드를 찾아준다.
public @interface RequestMapping {
	//이 안에 들어가는 문자열로 구분할 것이다.
	public String value();
}
