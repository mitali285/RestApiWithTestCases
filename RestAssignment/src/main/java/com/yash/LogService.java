package com.yash;

import javax.persistence.JoinTable;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogService {
	
	//execution(* com.deere.dpps.services.impl.* .*(..))
	@Pointcut(value = "execution(* com.yash.EmployeeController.get*(..))")
	public void method1(){
		
	}
	
/*	@Pointcut(value = "execution(* com.yash.spring.aop.CustomerService.up*(..))")
	public void method2(){
		
	}*/
	
	/*@Before("method1() or method2()")*/
	@Before("method1()")
	public void logBefore(JoinPoint joinpoint){
		System.out.println("LogService logBefore()");
		System.out.println("Method Which Call---"+joinpoint.getSignature().getName());
	}
	
	@AfterReturning("method1()")
	public void logReturning(){
		System.out.println("LogService logReturning()");
		
	}
	
	@AfterThrowing("method1()")
	public void logThrowing(){
		System.out.println("LogService logThrowing ()");
		
	}
	
	@After("method1()")
	public void logAfter(){
		System.out.println("LogService logAfter ()");
		
	}
	
	
}
