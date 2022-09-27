package com.creativeminds.app;
import com.creativeminds.app.handler.CustomSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired CustomSuccessHandler customSuccessHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT correo,password,estado FROM empleado WHERE correo=?")
                .authoritiesByUsernameQuery("SELECT correo,rol FROM empleado WHERE correo=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/verMovimientos").hasAnyRole("Admin","Operario")
                .antMatchers("/").hasAnyRole("Admin","Operario")
                .and().formLogin().successHandler(customSuccessHandler)
                .and().exceptionHandling().accessDeniedPage("/accesoDenegado")
                .and().logout().permitAll();
    }
}
