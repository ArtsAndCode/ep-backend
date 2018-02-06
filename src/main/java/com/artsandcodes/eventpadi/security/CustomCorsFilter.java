package com.artsandcodes.eventpadi.security;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * Created by prestige on 1/19/2018.
 */
public class CustomCorsFilter extends CorsFilter {

    public CustomCorsFilter() {
        super(configurationSource());
    }

    private static UrlBasedCorsConfigurationSource configurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedHeader("*");
        config.setMaxAge(36000L);
        config.setAllowedMethods(Arrays.asList("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }


//    private static UrlBasedCorsConfigurationSource configurationSource() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(false);
//        config.addAllowedOrigin("http://localhost:3000");
//        config.addAllowedOrigin("http://localhost:4200");
//        config.setMaxAge(36000L);
//        config.setAllowedMethods(Arrays.asList("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS"));
//        config.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//        return source;
//    }


//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//
//        HttpServletResponse response = (HttpServletResponse) res;
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "*");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "*");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.addHeader("Access-Control-Expose-Headers", "*");
//
//        HttpServletRequest request = (HttpServletRequest)req;
//        if (request.getMethod().equals("OPTIONS"))
//            response.setStatus(HttpServletResponse.SC_OK);
//        else
//            chain.doFilter(request, response);
//    }

}
