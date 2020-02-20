package ru.andnovikov.sportnow.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.andnovikov.sportnow.security.AuthoritiesConstants;
import ru.andnovikov.sportnow.security.jwt.JwtConfigurer;
import ru.andnovikov.sportnow.security.jwt.JwtTokenProvider;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/webjars/springfox-swagger-ui/**")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/v2/api-docs")
                .antMatchers("/h2-console/**");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //.and()
                //.authorizeRequests().antMatchers("/**").permitAll()
                //.and()
                .authorizeRequests().antMatchers("/swagger-ui.html").hasAuthority(AuthoritiesConstants.ADMIN)
                .and()
                .authorizeRequests().antMatchers("/event-list").hasAuthority(AuthoritiesConstants.ADMIN)
                .and()
                .formLogin()
                .and()
                .rememberMe()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/");
    }

}