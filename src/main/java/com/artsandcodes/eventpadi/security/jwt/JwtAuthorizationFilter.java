package com.artsandcodes.eventpadi.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swifta.omnibranches.dto.ErrorResponse;
import com.swifta.omnibranches.service.JwtTokenService;
import com.swifta.omnibranches.utility.AppConstants;
import com.swifta.omnibranches.utility.UtilityService;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by prestige on 11/27/2017.
 */
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    JwtConfig jwtConfig;
    JwtTokenService jwtTokenService;
    ErrorResponse errorResponse;
    UtilityService utilityService;

    private final Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    public JwtAuthorizationFilter() {
        super();
        this.jwtTokenService = new JwtTokenService();
        this.jwtConfig = new JwtConfig();
        this.errorResponse = new ErrorResponse();
        this.utilityService = new UtilityService();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = request.getHeader(this.jwtConfig.getHeader());
            log.info("Access Token : " + token);
            if (token == null) {

                filterChain.doFilter(request, response);
                return;
            }

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = getAuthentication(request);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            filterChain.doFilter(request, response);
        }
        catch(ExpiredJwtException expiredJwtException){

            response.setStatus(HttpStatus.UNAUTHORIZED.value());

            this.errorResponse.setError(AppConstants.TOKEN_EXPIRED_ERROR);
            this.errorResponse.setMessage("Token has expired");
            this.errorResponse.setTimestamp(this.utilityService.getCurrentTimeStamp());
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getWriter(),this.errorResponse);
        }
        catch (MalformedJwtException ex){

            response.setStatus(HttpStatus.UNAUTHORIZED.value());

            this.errorResponse.setError(AppConstants.TOKEN_MALFORMED_ERROR);
            this.errorResponse.setMessage("Malformed token");
            this.errorResponse.setTimestamp(this.utilityService.getCurrentTimeStamp());
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getWriter(),this.errorResponse);
        }
        catch (SignatureException sex){

            response.setStatus(HttpStatus.UNAUTHORIZED.value());

            this.errorResponse.setError(AppConstants.TOKEN_SIGNATURE_ERROR);
            this.errorResponse.setMessage("Malformed token");
            this.errorResponse.setTimestamp(this.utilityService.getCurrentTimeStamp());
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getWriter(),this.errorResponse);
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){

        String token = request.getHeader(this.jwtConfig.getHeader());

        if(token != null){
            return this.jwtTokenService.verifyToken(token);
        }

        return null;
    }


}
