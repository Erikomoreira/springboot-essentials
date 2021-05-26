package br.com.erik.springboot.config;

import br.com.erik.springboot.service.DevDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@EnableWebSecurity
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
@SuppressWarnings("java:S5344")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final DevDetailsService userAuthenticationDetailsService;

    /*
       BasicAuthenticationFilter
       UsernamePasswordAuthenticationFilter
       DefaultLoginPageGeneratingFilter
       DefaultLogoutPageGeneratingFilter
       FilterSecurityInterceptor
       Authentication -> Authorization
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                // csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        var passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        log.info("Password encoded {}", passwordEncoder.encode("test"));

        //tenta esse primeiro
        auth.inMemoryAuthentication()
                .withUser("erik2")
                .password(passwordEncoder.encode("test"))
                .roles("USER", "ADMIN")
                .and()
                .withUser("bob2")
                .password(passwordEncoder.encode("test"))
                .roles("USER");

        //não localizou tenta esse
        auth.userDetailsService(userAuthenticationDetailsService).passwordEncoder(passwordEncoder);

    }

}
