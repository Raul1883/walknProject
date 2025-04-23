package com.night.backendWalkn.logger;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

@Aspect
@Component
@Log4j2
@RequiredArgsConstructor
public class ServiceLoggingAspect {
    private final MessageSource messageSource;


    @Pointcut("execution(* com.night.backendWalkn.service.*.*(..))")
    public void callAtMyService() {
    }


    @Before("callAtMyService()")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint
                .getSignature()
                .getName();
        String invocationMsg = messageSource.getMessage(
                "log.method.invocation", new Object[]{methodName}, Locale.getDefault());
        log.info(invocationMsg);

        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            String parameters = Arrays
                    .stream(args)
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            String paramsMsg = messageSource.getMessage(
                    "log.method.parameters", new Object[]{parameters}, Locale.getDefault());
            log.info(paramsMsg);
        }
    }

    @Around("callAtMyService()")
    public Object measureMethodExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object returnValue = pjp.proceed();
        long end = System.currentTimeMillis();


        String methodName = pjp
                .getSignature()
                .getName();
        String resultInfo = messageSource.getMessage(
                "log.method.result",
                new Object[]{methodName, Long.toString(end - start), returnValue},
                Locale.getDefault()
                                                    );
        log.info(resultInfo);

        Object[] args = pjp.getArgs();
        if (args.length > 0) {
            String parameters = Arrays
                    .stream(args)
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            String paramsMsg = messageSource.getMessage(
                    "log.method.parameters", new Object[]{parameters}, Locale.getDefault());
            log.info(paramsMsg);
        }

        return returnValue;
    }

    @AfterThrowing("callAtMyService()")
    public void logException(JoinPoint joinPoint, Exception exception) {
        String methodName = joinPoint
                .getSignature()
                .getName();
        String exceptionMsg = messageSource.getMessage("log.method.exception",
                new Object[]{methodName, exception.getMessage()}, Locale.getDefault()
                                                      );
        log.error(exceptionMsg, exception);
    }


}
