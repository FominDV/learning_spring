package ru.geekbrains.summer.market.aspects;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceProfilingAspect {

    StopWatch stopWatch;

    @Getter
    Map<String, Long> serviceProfilingMap;

    @PostConstruct
    private void init() {
        stopWatch = new StopWatch();
        serviceProfilingMap = new HashMap<>();
    }

    @Around("execution(public * ru.geekbrains.summer.market.services.*.*(..))")
    public Object serviceProfiling(ProceedingJoinPoint joinPoint) throws Throwable {
        Object returnedValue;
        try {
            if (!stopWatch.isRunning()) {
                stopWatch.start();
            }
            returnedValue = joinPoint.proceed();
        } finally {
            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }
            addStatistic(joinPoint);
        }
        return returnedValue;
    }

    private void addStatistic(JoinPoint joinPoint) {
        String serviceName = joinPoint.getSignature().getDeclaringType().getSimpleName();
        serviceProfilingMap.merge(serviceName, stopWatch.getLastTaskTimeMillis(), Long::sum);
    }

}
