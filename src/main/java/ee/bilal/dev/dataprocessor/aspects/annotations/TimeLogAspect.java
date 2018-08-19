package ee.bilal.dev.dataprocessor.aspects.annotations;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TimeLogAspect {
    @Around("@annotation(ee.bilal.dev.dataprocessor.aspects.annotations.Timed) && execution(public * *(..))")
    public Object time(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object value;

        try {
            value = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            log.error("TimeLogAspect threw '{}'", throwable);
            throw throwable;
        } finally {
            final long duration = System.currentTimeMillis() - start;
            final Signature signature = proceedingJoinPoint.getSignature();

            log.info("{}.{} took {} ms", signature.getDeclaringType().getSimpleName(),
                    signature.getName(), duration);
        }

        return value;
    }
}
