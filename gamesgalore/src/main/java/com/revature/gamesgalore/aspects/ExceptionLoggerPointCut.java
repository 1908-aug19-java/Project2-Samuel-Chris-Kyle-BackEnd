package com.revature.gamesgalore.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ExceptionLoggerPointCut {

	private static Logger logger = LogManager.getLogger();

	@AfterThrowing(pointcut = "within(com.revature.gamesgalore..*)", throwing = "ex")
	public void logError(JoinPoint jp, Exception ex) {
		logger.error("Method " + jp.getSignature() + " threw an exception: " + ex + ". Originating from class " + jp.getTarget().getClass());
	}
}
