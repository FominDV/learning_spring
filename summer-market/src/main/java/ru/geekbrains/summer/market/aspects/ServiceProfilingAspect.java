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

    @Getter
    Map<String, StopWatch> serviceProfilingMap;

    @PostConstruct
    private void init() {
        serviceProfilingMap = new HashMap<>();
    }

    @Around("execution(public * ru.geekbrains.summer.market.services.*.*(..))")
    public Object serviceProfiling(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = getCurrentStopWatch(joinPoint);
        Object returnedValue;
        try {
            stopWatch.start();
            returnedValue = joinPoint.proceed();
        } finally {
            stopWatch.stop();
        }
        return returnedValue;
    }

    private StopWatch getCurrentStopWatch(JoinPoint joinPoint) {
        String serviceName = joinPoint.getSignature().getDeclaringType().getSimpleName();
        if (serviceProfilingMap.containsKey(serviceName)) {
            return serviceProfilingMap.get(serviceName);
        }
        StopWatch stopWatch = new StopWatch();
        serviceProfilingMap.put(serviceName, stopWatch);
        return stopWatch;
    }

}
