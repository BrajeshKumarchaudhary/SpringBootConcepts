package com.app.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Aspect
public class RestControllerLogging {
	private static final Logger LOGGER = LoggerFactory.getLogger(RestControllerLogging.class);

	@Pointcut("execution(* com.app.*.*.*(..))") // logger whole Application
	//"execution(Any Return Type com.app.anyPackage.anyClass.anyMethod(..no of arguments))"
	public void controllerLoggingPointcut() {

	}

//	@Before("controllerLoggingPointcut()")
//	public void logRequest(JoinPoint joinPoint) {
//		LOGGER.info("request for - {} {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
//	}

//	@AfterReturning(pointcut = "controllerLoggingPointcut()", returning = "result")
//	public void logAfter(JoinPoint joinPoint, Object result) {
//		String response = this.getValue(result);
//		LOGGER.info("response of {} {}", joinPoint.getSignature().getName(), response);
//	}

	//@Around Advice is the combination of before and after
	@Around("controllerLoggingPointcut()")
	public Object ApplicationLogger(ProceedingJoinPoint pjp) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		String methodName = pjp.getSignature().getName();
		String className = pjp.getTarget().getClass().toString();
		Object[] array = pjp.getArgs();
//		LOGGER.info("method invoked:" + methodName + "()  className:" + className + "   Request:"
//				+ mapper.writeValueAsString(array)); 
		Object object = pjp.proceed();
//		LOGGER.info(methodName + " className " + className + "  Response:" + mapper.writeValueAsString(object));
		return object;
	}
	
	
	
	@Around("@annotation(ApplicationLog)")
	public Object ApplicationLoggerByAnnotation(ProceedingJoinPoint pjp) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		String methodName = pjp.getSignature().getName();
		String className = pjp.getTarget().getClass().toString();
		Object[] array = pjp.getArgs();
		LOGGER.info("method invoked:" + methodName + "()  className:" + className + "   Request:"
				+ mapper.writeValueAsString(array)); 
		Object object = pjp.proceed();
		LOGGER.info(methodName + " className " + className + "  Response:" + mapper.writeValueAsString(object));
		return object;
	}
	
	
	@Around("@annotation(LogExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		 
	    Object proceed = pjp.proceed();
	 
	    long executionTime = System.currentTimeMillis() - start;
	 
	    LOGGER.info(pjp.getSignature() + " executed in " + executionTime + "ms");
	    return proceed;
	}

	@AfterThrowing(pointcut  = "@annotation(ExceptionThrow)",throwing="ex")
//	@AfterThrowing(pointcut= "execution(* com.app.*.*.*(..))",throwing="ex")
	public Object logExecption(JoinPoint pjp,Exception ex) throws Throwable {
		String methodName = pjp.getSignature().getName();
		String className = pjp.getTarget().getClass().toString();
	    LOGGER.info("Exception Occuered at "+methodName +" In class"+className);
	    LOGGER.info("Exception is  "+ex.getMessage());
	    return pjp;
	}

	
	
	private String getValue(Object result) {
		String response = null;
		if (null != result) {
			if (result.toString().endsWith("@" + Integer.toHexString(result.hashCode()))) {
				response = result.toString();
			} else {
				response = result.toString();
			}
		}
		return response;
	}
}
