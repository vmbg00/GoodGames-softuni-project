package bg.softuni.gamingstore.aspect;

import bg.softuni.gamingstore.models.entities.PictureEntity;
import bg.softuni.gamingstore.repositories.PicturesRepository;
import bg.softuni.gamingstore.services.impl.GameServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class GalleryAddServiceAspect {

    private Logger LOGGER = LoggerFactory.getLogger(GameServiceImpl.class);
    private final PicturesRepository picturesRepository;

    public GalleryAddServiceAspect(PicturesRepository picturesRepository) {
        this.picturesRepository = picturesRepository;
    }

    @Pointcut("execution(* bg.softuni.gamingstore.services.impl.GalleryServiceImpl.add(..))")
    public void track(){

    }

    @After(value = "track()")
    public void afterAdvice(JoinPoint joinPoint){
        LOGGER.info("After advice detected!");
        LOGGER.info("After method: " + joinPoint.getSignature());
        List<PictureEntity> pictureEntityList = picturesRepository.findAll();
        LOGGER.info("Added picture with name -> " + pictureEntityList.get(pictureEntityList.size() - 1).getTitle());
    }
}
