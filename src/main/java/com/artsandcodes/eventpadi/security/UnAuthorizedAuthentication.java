package com.artsandcodes.eventpadi.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by prestige on 1/19/2018.
 */
public class UnAuthorizedAuthentication implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        response.sendError(HttpStatus.UNAUTHORIZED.value(), "You don't have permission to view this resource");
    }
}
