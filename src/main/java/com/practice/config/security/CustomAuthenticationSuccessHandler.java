package com.practice.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
// 인증 성공 후에 실제로 핸들링 하는 부분
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response
            , Authentication authentication) throws IOException
            , ServletException {
        // 사용자가 마지막에 접속했던 URL
        // 이 부분은 수정할 필요가 있다. ( 마지막 접속 URL을 전체적으로 손 봐야 한다. )
        SavedRequest saveRequest = requestCache.getRequest(request, response);
        if ( saveRequest != null && !saveRequest.getRedirectUrl().contains("error")) {
            String targetUrl = saveRequest.getRedirectUrl();
            redirectStrategy.sendRedirect(request, response, targetUrl);
        }
        else {
            setDefaultTargetUrl("/");
            redirectStrategy.sendRedirect(request, response, getDefaultTargetUrl());
        }
    }
}
