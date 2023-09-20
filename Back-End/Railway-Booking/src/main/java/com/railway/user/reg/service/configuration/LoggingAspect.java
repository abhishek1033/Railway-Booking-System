package com.railway.user.reg.service.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

	@Around("execution(public * com.railway.*.*.*.*.*.*(..))")
	public Object aroundLog(final ProceedingJoinPoint j) throws Throwable {

		log.info(" " + j.getSignature().getDeclaringTypeName() + " : " + j.getSignature().getName() + " : START ");
		final Object value = j.proceed();

		log.info(" " + j.getSignature().getDeclaringTypeName() + " : " + j.getSignature().getName() + " : END ");
		return value;
	}

	@AfterThrowing(pointcut = "execution(public * com.railway.*.*.*.*.*.*(..))", throwing = "error")
	public void logAfterThrowing(final JoinPoint j, final Throwable error) {

		log.error(" " + j.getSignature().getDeclaringTypeName() + " : " + j.getSignature().getName() + " : Exception : "
				+ error.getMessage() + error);
	}

	/*
	@AfterReturning(value = "execution(public * com.railway.*.*.*.*.*.*(..))", returning = "result")
	public void afterReturning(final JoinPoint joinPoint, final Object result) {
		log.info("{} returned with value {}", joinPoint, result);
	}
	  
	@After(value = "execution(public * com.railway.*.*.*.*.*.*(..))")
	public void after(final JoinPoint joinPoint) {

		log.info("after execution of {}", joinPoint);
	}
	  
	@Before("execution(public * com.railway.*.*.*.*.*.*(..))")
	public void before(final JoinPoint joinPoint) {
		log.info(" Check for user access ");
		log.info(" Allowed execution for {}", joinPoint);
	}
	 */

}
