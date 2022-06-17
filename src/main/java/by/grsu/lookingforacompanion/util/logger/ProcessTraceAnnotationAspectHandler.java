package by.grsu.lookingforacompanion.util.logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ProcessTraceAnnotationAspectHandler {

    @Around("@annotation(by.grsu.lookingforacompanion.util.logger.ProcessTrace)")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        final Object proceed = joinPoint.proceed();

        log.info(joinPoint + " execution time: " + (System.currentTimeMillis() - startTime) + "MS");

        return proceed;
    }

}
