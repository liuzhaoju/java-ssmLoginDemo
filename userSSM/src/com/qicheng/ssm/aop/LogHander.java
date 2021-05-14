package com.qicheng.ssm.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LogHander {
	final Logger logger = Logger.getLogger(LogHander.class);
	
	public void doBefore(JoinPoint joinPoint) {
//	    System.out.println("==========执行controller前置通知===============");
	    String params = ""; 
	     if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) { 
	       for ( int i = 0; i < joinPoint.getArgs().length; i++) { 
	        params += joinPoint.getArgs()[i] + ";"; 
	      } 
	    } 
	     logger.info(" before: " + joinPoint.getTarget().getClass().getName() + " : "+joinPoint.getSignature().getName()+ "  :  "+params);
	  }
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable{//不能用try catch 否则事物不生效
//	     System.out.println("==========开始执行controller环绕通知===============");
	     long start = System.currentTimeMillis();
	     Object ob=null;
//	     try {
	    	ob =joinPoint.proceed();
	       long end = System.currentTimeMillis();
	       if(logger.isInfoEnabled()){
	         logger.info(" around " + joinPoint + "\tUse time : " + (end - start) + " ms!");
	       }
//	       System.out.println("==========结束执行controller环绕通知===============");
//	     } catch (Throwable e) {
//	       long end = System.currentTimeMillis();
//	       if(logger.isInfoEnabled()){
//	         logger.info(" around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
//	       }
//	     }
	     return ob;
	   }
	
	public void after(JoinPoint joinPoint) { 
		String params = ""; 
	     if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) { 
	       for ( int i = 0; i < joinPoint.getArgs().length; i++) { 
	        params += joinPoint.getArgs()[i] + ";"; 
	      } 
	    } 
	     logger.info(" after : "+joinPoint.getTarget().getClass().getName()+ " : " + joinPoint.getSignature().getName()+ " : "+ params);
	  }
	public void afterReturn(Object obj){
//	     System.out.println("=====执行controller后置返回通知====="); 
	       if(logger.isInfoEnabled()){
	         logger.info(" afterReturn " + obj);
	       }
	   }
//	@Override
//
//	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//
//	   Object result;
//
//	   try {
//
//	       //@Before
//
//	       result = method.invoke(target, args);
//
//	       //@AfterReturning
//
//	       return result;
//
//	   } catch (InvocationTargetException e) {
//
//	       Throwable targetException = e.getTargetException();
//
//	       //@AfterThrowing
//
//	       throw targetException;
//
//	   } finally {
//
//	       //@After
//
//	   }
//
//	}
}
