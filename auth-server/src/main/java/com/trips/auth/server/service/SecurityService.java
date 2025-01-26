package com.trips.auth.server.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    public String getBearerToken(HttpServletRequest request){
        String bearerToken = null;
        String authorization = request.getHeader("Authorization");
        if(authorization != null &&  authorization.startsWith("Bearer ")){
            bearerToken = authorization.substring(7);
        }
        return bearerToken;
    }

}
