package bg.softuni.gamingstore.config;


import bg.softuni.gamingstore.services.impl.GoodGamesUserServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final GoodGamesUserServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(GoodGamesUserServiceImpl userDetailsService,
                                  PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().requireCsrfProtectionMatcher(new AllExceptUrlsStartedWith("/gallery/add"));

        http.
                authorizeRequests().
                // allow access to static resources to anyone
                        antMatchers("/assets/**").permitAll().
                // allow access to home, user login and registration to anyone
                        antMatchers("/", "/users/login", "/users/register").permitAll().
                // protect all other pages
                        antMatchers("/**").authenticated().
                and().
                // configure login with HTML form
                        formLogin().
                // our login page will be served by the controller with mapping /users/login
                        loginPage("/users/login").
                // the name of the user name input field in OUR login form is username (optional)
                        usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                // the name of the user password input field in OUR login form is password (optional)
                        passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                // on login success redirect here
                        defaultSuccessUrl("/").
                // on login failure redirect here
                        failureForwardUrl("/users/login-error").
                and().
                logout().
                logoutUrl("/users/logout").
                logoutSuccessUrl("/").
                invalidateHttpSession(true).
                deleteCookies("JSESSIONID").
                logoutRequestMatcher(new AntPathRequestMatcher("/users/logout"));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                userDetailsService(userDetailsService).
                passwordEncoder(passwordEncoder);
    }

    private static class AllExceptUrlsStartedWith implements RequestMatcher {

        private static final String[] ALLOWED_METHODS =
                new String[] {"GET", "HEAD", "TRACE", "OPTIONS"};

        private final String[] allowedUrls;

        public AllExceptUrlsStartedWith(String... allowedUrls) {
            this.allowedUrls = allowedUrls;
        }

        @Override
        public boolean matches(HttpServletRequest request) {
            // replicate default behavior (see CsrfFilter.DefaultRequiresCsrfMatcher class)
            String method = request.getMethod();
            for (String allowedMethod : ALLOWED_METHODS) {
                if (allowedMethod.equals(method)) {
                    return false;
                }
            }

            // apply our own exceptions
            String uri = request.getRequestURI();
            for (String allowedUrl : allowedUrls) {
                if (uri.startsWith(allowedUrl)) {
                    return false;
                }
            }

            return true;
        }

    }
}
