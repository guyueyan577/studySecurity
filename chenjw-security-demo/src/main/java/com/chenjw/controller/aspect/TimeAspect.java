package com.chenjw.controller.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

@Aspect   //声明类为一个切片类
@Component   //声明这个类是一个容器 
public class TimeAspect {

	/**
	 * 作用范围，第一个*表示任意返回值，后边紧跟范围com.chenjw.controller.UserController中任意接口，最后的（..）表示任意参数
	 * @throws Throwable 
	 */
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
		
		//业务前调用
		System.out.println("TimeAspect start!");
		
		Object[] args = pjp.getArgs();  //要执行方法的参数
		for (Object arg : args) {
			System.out.println("arg is " + arg);
		}
		
		Object obj = pjp.proceed();  //调用被拦截的方法
		
		System.out.println("return is :" + JSONObject.toJSONString(obj));
		
		//业务后调用
		System.out.println("TimeAspect end!");
		return obj;
	}
}
