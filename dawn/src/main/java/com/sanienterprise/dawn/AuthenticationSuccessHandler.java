package com.sanienterprise.dawn;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    
    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request, 
        HttpServletResponse response,
        Authentication authentication
    ) throws ServletException, IOException {

        boolean isAdmin = authentication
                            .getAuthorities()
                            .stream()
                            .anyMatch(
                                t -> t
                                    .getAuthority()
                                    .equals("ROLE_ADMIN")
                            );
        if(isAdmin) {
            setDefaultTargetUrl("/admin/home"); //set default redirect admin page pag after admin logs in
        } else {
            setDefaultTargetUrl("/user/account"); //set default redirect page after customer logs in
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
