package controller;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
//Class찾기
@Retention(RUNTIME)
@Target(TYPE)
public @interface Controller {

}
