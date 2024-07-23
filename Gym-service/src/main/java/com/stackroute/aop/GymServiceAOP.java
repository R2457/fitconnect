package com.stackroute.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class GymServiceAOP {

    @Before("execution(* com.stackroute.service.GymService.*(..))")
    public void beforeMethodExecution(JoinPoint joinPoint) {
        System.out.println("Before method execution: " + joinPoint.getSignature().toShortString());
        printLine();
    }

    @AfterReturning(pointcut = "execution(* com.stackroute.service.GymService.*(..))", returning = "result")
    public void afterMethodExecution(JoinPoint joinPoint, Object result) {
        System.out.println("After method execution: " + joinPoint.getSignature().toShortString());
        System.out.println("Method returned: " + result);
        printLine();
    }

    @AfterThrowing(pointcut = "execution(* com.stackroute.service.GymService.*(..))", throwing = "exception")
    public void afterMethodException(JoinPoint joinPoint, Exception exception) {
        System.out.println("Exception thrown from method: " + joinPoint.getSignature().toShortString());
        System.out.println("Exception message: " + exception.getMessage());
        printLine();
    }
    
	private void printLine() {
		System.out.println("---------------------------------------------------------- ");
	}

}

