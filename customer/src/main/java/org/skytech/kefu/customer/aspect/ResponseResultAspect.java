package org.skytech.kefu.customer.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
//@Aspect
//@Component
public class ResponseResultAspect {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void Pointcut() {
    }

    @After("Pointcut()")
    public void doAspect(JoinPoint point) {
        log.info("==================后置执行完成==================>");
    }
}
