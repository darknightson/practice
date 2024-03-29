package com.practice.config.security;

/**
 * @author  : anthony.son
 * @since   : 2021. 05
 * @version : 1.0
 */

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 인증 실패 후 처리
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = "Invalid username or password";

        if ( exception instanceof BadCredentialsException ) {
        }
        else if ( exception instanceof InsufficientAuthenticationException ) {
            errorMessage = "Invalid Secret key";
        }
        setDefaultFailureUrl("/login?error=true&exception=" + exception.getMessage());
        super.onAuthenticationFailure(request, response, exception);
    }
}
