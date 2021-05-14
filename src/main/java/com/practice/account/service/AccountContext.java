package com.practice.account.service;

/**
 * @author  : anthony.son
 * @since   : 2021. 05
 * @version : 1.0
 */

import com.practice.account.vo.AccountVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AccountContext extends User {

    private AccountVo accountVo;

    public AccountContext(AccountVo accountVo, Collection<? extends GrantedAuthority> authorities) {
        super(accountVo.getUserId(), accountVo.getPassword(), authorities);
        this.accountVo = accountVo;
    }

    public AccountVo getAccountVo() {
        return accountVo;
    }
}
