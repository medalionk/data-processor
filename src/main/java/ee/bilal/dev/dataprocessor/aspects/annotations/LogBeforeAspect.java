package ee.bilal.dev.dataprocessor.aspects.annotations;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LogBeforeAspect {

    /**
     * Log function name and passed parameters at start of function
     * @param joinPoint function join point
     */
    @Before("@annotation(ee.bilal.dev.dataprocessor.aspects.annotations.LogBefore))")
    public void before(final JoinPoint joinPoint){
        final String functionName = joinPoint.toLongString();
        final String functionArgs = Arrays.toString(joinPoint.getArgs());

        log.info("Call function '{}' with arguments '{}'", functionName, functionArgs);
    }

}
