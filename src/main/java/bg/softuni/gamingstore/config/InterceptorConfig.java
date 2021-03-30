package bg.softuni.gamingstore.config;

import bg.softuni.gamingstore.interceptors.FaviconInterceptor;
import bg.softuni.gamingstore.interceptors.TraceIdInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private final TraceIdInterceptor traceIdInterceptor;
    private final FaviconInterceptor faviconInterceptor;

    public InterceptorConfig(TraceIdInterceptor traceIdInterceptor, FaviconInterceptor faviconInterceptor) {
        this.traceIdInterceptor = traceIdInterceptor;
        this.faviconInterceptor = faviconInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(faviconInterceptor);
        registry.addInterceptor(traceIdInterceptor);
    }
}
