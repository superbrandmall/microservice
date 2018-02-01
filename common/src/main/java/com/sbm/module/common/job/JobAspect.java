package com.sbm.module.common.job;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class JobAspect {

	@Pointcut("@annotation(a)")
	public void declareJoinPointExpression(Scheduled a) {

	}

	@Before("declareJoinPointExpression(a)")
	public void before(JoinPoint joinPoint, Scheduled a) {
		log.info(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + ": started ");
	}

	@After("declareJoinPointExpression(a)")
	public void after(JoinPoint joinPoint, Scheduled a) {

	}

	@AfterReturning(value = "declareJoinPointExpression(a)", returning = "result")
	public void afterReturnning(JoinPoint joinPoint, Object result, Scheduled a) {
		log.info(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + ": completed ");
	}

	@AfterThrowing(value = "declareJoinPointExpression(a)", throwing = "e")
	public void afterThrowing(JoinPoint joinPoint, Exception e, Scheduled a) {

	}
}
