package bg.softuni.gamingstore.config;

import bg.softuni.gamingstore.interceptors.FaviconInterceptor;
import bg.softuni.gamingstore.interceptors.HomeInterceptor;
import bg.softuni.gamingstore.interceptors.UsersInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HomeInterceptor()).addPathPatterns("/");
        registry.addInterceptor(new UsersInterceptor()).addPathPatterns("/users/login", "/users/register");
        registry.addInterceptor(new FaviconInterceptor());
    }
}
