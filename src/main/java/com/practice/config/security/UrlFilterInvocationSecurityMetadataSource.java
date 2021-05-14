package com.practice.config.security;

/**
 * @author  : anthony.son
 * @since   : 2021. 05
 * @version : 1.0
 */

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    // db에서 가져온 값이 저장되는 객체
    private LinkedHashMap<RequestMatcher, List<ConfigAttribute>> requestMap = new LinkedHashMap<>();

    // 권한 목록 호출
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        // 사용자가 어떤 url로 요청했는지를 확인한다.
        HttpServletRequest request = ((FilterInvocation) o).getRequest();
        // DB에서 가져오게끔 세팅
        requestMap.put(new AntPathRequestMatcher("/mypage"), Arrays.asList(new SecurityConfig("ROLE_USER")));
        // 권한 정보 추출
        if ( requestMap != null ) {
            for ( Map.Entry<RequestMatcher, List<ConfigAttribute>> entries : requestMap.entrySet() ) {
                RequestMatcher matcher = entries.getKey();
                if ( matcher.matches(request) ) {
                    return entries.getValue(); // 매칭되는 권한 정보를 리턴한다.
                }
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<>();

        for ( Map.Entry<RequestMatcher, List<ConfigAttribute>> entries : requestMap.entrySet() ) {
            allAttributes.addAll(entries.getValue());
        }
        return allAttributes;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
