package bg.softuni.gamingstore.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class FaviconInterceptor implements HandlerInterceptor {

    private final Logger LOGGER = LoggerFactory.getLogger(FaviconInterceptor.class);

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String link = "static/assets/images/favicon.png";

        if (modelAndView != null) {
            modelAndView.addObject("favicon", link);
        }

        LOGGER.info("Favicon interceptor postHandle method");
    }
}
