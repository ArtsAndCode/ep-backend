package com.artsandcodes.eventpadi.security;

import com.swifta.omnibranches.security.jwt.JwtAuthenticationFilter;
import com.swifta.omnibranches.security.jwt.JwtAuthorizationFilter;
import com.swifta.omnibranches.security.jwt.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

/**
 * Created by prestige on 11/27/2017.
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    UserDetailsService userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtConfig jwtConfig;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/loginSuccess","/v2/api-docs", "/configuration/**",
                "/swagger-resources/**", "/swagger-ui.html", "/webjars/**",
                "/refreshToken/**","/password/reset","/password/create");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        http
            .exceptionHandling().authenticationEntryPoint(new UnAuthorizedAuthentication()).and()
            .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .antMatchers("/admins/create").hasAnyRole("CREATE_ADMIN","ALL")
                .antMatchers("/admins/update/**").hasAnyRole("UPDATE_ADMIN","ALL")
                .antMatchers("/admins/delete/**").hasAnyRole("DELETE_ADMIN","ALL")
                .antMatchers("/admins/profile").hasAnyRole("VIEW_ADMIN","ALL")
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new CustomCorsFilter(), UsernamePasswordAuthenticationFilter.class)//CorsFilter.class)
                .addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    }


}
