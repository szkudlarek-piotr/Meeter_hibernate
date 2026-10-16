package org.example.meeter.aspects;

import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspects {

    @Before("execution(* org.example.meeter..*.*(..))")
    public void logFunction(JoinPoint joinPoint) {
        System.out.println(
                "Wykonuję metodę " + joinPoint.getSignature().getName()
        );
    }
}
