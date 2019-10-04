package com.revature.gamesgalore.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.revature.gamesgalore.exceptions.ResponseExceptionManager;

@Aspect
@Component
public class ExceptionLoggerPointCut {

	private static Logger logger = LogManager.getLogger();

	@AfterThrowing(pointcut = "within(com.revature.gamesgalore..*)", throwing = "ex")
	public void logError(JoinPoint jp, Exception ex) throws Exception {
		logger.error("Method " + jp.getSignature() + " threw an exception: " + ex + ". Originating from class " + jp.getTarget().getClass());
		if(ex instanceof ResponseStatusException) {
			throw ex;
		}else {
			throw ResponseExceptionManager
			.getRSE(HttpStatus.INTERNAL_SERVER_ERROR, ResponseExceptionManager.UNEXPECTED_ERROR).get();
		}
	}
}
