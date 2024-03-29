package com.practice.account.service;

/**
 * @author  : anthony.son
 * @since   : 2021. 05
 * @version : 1.0
 */

import com.practice.account.dao.AccountDao;
import com.practice.account.vo.AccountVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountDao accountDao;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        AccountVo accountVo = accountDao.selectAccountId(userId);

        if ( accountVo == null ) {
            throw  new UsernameNotFoundException("usernameNotFoundException");
        }
        List<GrantedAuthority> roles = new ArrayList<>();
        //roles.add(new SimpleGrantedAuthority(accountVo.getRole()));
        roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        AccountContext accountContext = new AccountContext(accountVo, roles);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return accountContext;
    }
}
