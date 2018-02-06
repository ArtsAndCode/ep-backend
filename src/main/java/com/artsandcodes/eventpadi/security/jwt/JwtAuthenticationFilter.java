package com.artsandcodes.eventpadi.security.jwt;


import com.artsandcodes.eventpadi.security.UserDetail;
import com.artsandcodes.eventpadi.service.JwtTokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by prestige on 11/27/2017.
 */

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    AuthenticationManager authenticationManager;

    JwtConfig jwtConfig;

    JwtTokenService jwtTokenService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {

        this.authenticationManager = authenticationManager;
        this.jwtConfig = new JwtConfig();
        this.jwtTokenService = new JwtTokenService();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            return this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,
                            password, new ArrayList<>())
            );

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth)
            throws IOException, ServletException {

        UserDetail userDetail = ((UserDetail) auth.getPrincipal());
        Map tokenResponse = this.jwtTokenService.generateToken(userDetail);

//        ObjectMapper mapper = new ObjectMapper();
//        mapper.writeValue(response.getWriter(),tokenResponse);

        String accessToken = tokenResponse.get("accessToken").toString();
        String refreshToken = tokenResponse.get("refreshToken").toString();

        request.setAttribute("accessToken", accessToken);
        request.setAttribute("refreshToken", refreshToken);
        request.setAttribute("username", userDetail.getUsername());

        request.getRequestDispatcher("/loginSuccess").forward(request,response);

    }

}
