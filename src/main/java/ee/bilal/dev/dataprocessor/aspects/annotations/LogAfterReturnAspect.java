package ee.bilal.dev.dataprocessor.aspects.annotations;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAfterReturnAspect {

    /**
     * Log function name and result on function return
     * @param joinPoint function joint point
     * @param result result from function
     */
    @AfterReturning(
            value = "@annotation(ee.bilal.dev.dataprocessor.aspects.annotations.LogAfterReturn))",
            returning = "result")
    public void afterReturning(final JoinPoint joinPoint, final Object result) {
        final String functionName = joinPoint.toLongString();

        log.info("Function '{}' returned with value {}", functionName, result);
    }

}
