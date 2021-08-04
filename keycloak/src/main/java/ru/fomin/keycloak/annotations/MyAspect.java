package ru.fomin.keycloak.annotations;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    @Pointcut("@within(ru.fomin.keycloak.annotations.MyAnnotation)")
    public void annotatedClass() {}

    @Before("annotatedClass()")
    public void printABit(JoinPoint joinPoint) {
        String value = joinPoint.getTarget().getClass().getAnnotation(MyAnnotation.class).value();
        ThreadLocalService.threadLocal.set(value);
    }

}
