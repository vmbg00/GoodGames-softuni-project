package bg.softuni.gamingstore.aspect;

import bg.softuni.gamingstore.services.impl.GameServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GalleryAddServiceAspect {

    private Logger LOGGER = LoggerFactory.getLogger(GameServiceImpl.class);

    @Pointcut("execution(* bg.softuni.gamingstore.services.impl.GalleryServiceImpl.add())")
    public void track(){

    }

    @After(value = "track()")
    public void afterAdvice(JoinPoint joinPoint){
        LOGGER.info("After method: " + joinPoint.getSignature());
    }
}
