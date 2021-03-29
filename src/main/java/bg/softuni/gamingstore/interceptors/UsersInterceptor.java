package bg.softuni.gamingstore.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsersInterceptor implements HandlerInterceptor {

    private final Logger LOGGER = LoggerFactory.getLogger(UsersInterceptor.class);

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOGGER.info("In postHandle method of UsersInterceptor (login/register)");
    }

}
