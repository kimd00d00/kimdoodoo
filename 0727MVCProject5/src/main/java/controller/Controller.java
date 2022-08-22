package controller;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
//Controller 어노테이션이 들어간다 = Model이다.
@Retention(RUNTIME)
@Target(TYPE)
public @interface Controller {
	
}
