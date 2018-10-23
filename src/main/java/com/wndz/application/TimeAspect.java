package com.wndz.application;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Around;

 
@Aspect
@Component
public class TimeAspect {
	
	//需要执行切面的方法范围
	@Around("execution(* com.wndz.application.HelloController..*(..))")
	public Object method(ProceedingJoinPoint pjp) throws Throwable {

		System.out.println("=====Aspect处理=======");
		Object[] args = pjp.getArgs();
		for (Object arg : args) {
			System.out.println("参数为:" + arg);
		}
		
		//方法前的增强
		long start = System.currentTimeMillis();
		
		//执行目标方法
		Object object = pjp.proceed();
		
		//方法后的增强
		System.out.println("Aspect 耗时:" + (System.currentTimeMillis() - start));

		return object;
	} 
	
}
