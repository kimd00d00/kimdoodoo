package controller;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
// @RequestMapping(URI)가 들어간다. => 사용자가 보내준 URI와 같을 때 정해진 기능을 수행한다.
@Retention(RUNTIME)
@Target(METHOD)
public @interface RequestMapping {
	public String value();
}
