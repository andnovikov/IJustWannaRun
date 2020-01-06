package ru.andnovikov.sportnow.aop.logging;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static Logger logger = LogManager.getLogger();

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controller() {
    }

    @Pointcut("execution ( * ru.andnovikov.sportnow..* (..))")
    public void allMethods() {
    }

    @Pointcut("execution(public * *(..))")
    protected void loggingPublicOperation() {
    }

    @Before("controller() && allMethods() && loggingPublicOperation()")
    public void logMethodCall(JoinPoint joinPoint) {
        if(logger.isDebugEnabled()) {
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();

            logger.debug("Method:  " + className + "." + methodName + "()"
                    + " has been called with input parameters: " + Arrays.toString(joinPoint.getArgs()));
        }
    }

    @AfterReturning(pointcut = "controller() && allMethods()", returning = "result")
    public void afterDebug(final JoinPoint joinPoint, final Object result) {
        if(logger.isDebugEnabled()) {
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            String value = result != null ? result.toString() : "";
            logger.debug("Method " + className + "." + methodName + "()" + " return value : " + value);
        }
    }

    @AfterThrowing(pointcut = "controller() && allMethods()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        logger.error("An exception has been thrown in " + joinPoint.getSignature().getName() + "()");
        logger.error("Cause : " + exception.getCause());
    }

    @Around("controller() && allMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();

        if (logger.isDebugEnabled()) {
            long elapsedTime = System.currentTimeMillis() - start;
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            logger.debug("Method " + className + "." + methodName + "()" + " execution time : " + elapsedTime + " ms");
        }

        return result;
    }

}
