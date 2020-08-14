package ru.relex.delivery.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.relex.delivery.commons.model.UserRole;
import ru.relex.delivery.db.mapper.UserAuthMapper;
import ru.relex.delivery.security.converter.DeliveryAuthenticationConverter;
import ru.relex.delivery.security.filter.JsonAuthenticationFilter;
import ru.relex.delivery.security.handler.DeliveryAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(
        jsr250Enabled = true,
        prePostEnabled = true,
        securedEnabled = true
)
@ComponentScan(basePackageClasses = SecurityConfig.class)

public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private final UserDetailsService userDetailsService;
    private final UserAuthMapper userMapper;

    @Autowired
    public SecurityConfig(final UserDetailsService userDetailsService,
                          final UserAuthMapper userMapper) {
        this.userDetailsService = userDetailsService;
        this.userMapper = userMapper;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder("", 178_123, 512);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) {
        final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        builder.authenticationProvider(provider);
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        final var authenticationFilter = new JsonAuthenticationFilter(authenticationManager(), new DeliveryAuthenticationConverter());
        authenticationFilter.setRequestMatcher(new AntPathRequestMatcher("/auth/login", "POST"));

        authenticationFilter.setSuccessHandler(new DeliveryAuthenticationSuccessHandler(userMapper));

        authenticationFilter.setFailureHandler(new AuthenticationFailureHandler() {
            private final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                logger.error("Error", exception);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        });


        http
                .csrf().csrfTokenRepository(csrfTokenRepository()).and()/*.disable()*/
                .cors().configurationSource(corsConfigurationSource()).and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/dishes/**", "/restaurants/**" ).permitAll()
                .antMatchers(HttpMethod.POST, "/users" ).permitAll()
                .antMatchers("/auth/**").not().authenticated()
                .antMatchers("/admin/**").hasAnyAuthority("ROLE_" + UserRole.ADMIN.name())
                .antMatchers("/logout").authenticated()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .addFilterAfter(authenticationFilter, LogoutFilter.class)
        ;

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api").allowedMethods("*")
                .allowedOrigins("*")
                .allowedMethods("*");
        registry.addMapping("/api/**").allowedMethods("*")
                .allowedOrigins("*")
                .allowedMethods("*");
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD",
                "GET", "POST", "PUT", "DELETE", "PATCH"));
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
        // configuration.setAllowCredentials(true);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        // configuration.setAllowedHeaders(Collections.unmodifiableList(Arrays.asList("Authorization", "Cache-Control", "Content-Type")));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    private CsrfTokenRepository csrfTokenRepository() {
        var csrfRepo = CookieCsrfTokenRepository.withHttpOnlyFalse();
        csrfRepo.setCookiePath("/");
        return csrfRepo;
    }


}
