package com.practice.account.service;

/**
 * @author  : anthony.son
 * @since   : 2021. 05
 * @version : 1.0
 */

import com.practice.account.dao.AccountDao;
import com.practice.account.vo.AccountVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountDao accountDao;

    @Transactional
    public void createAccount(AccountVo accountVo) {
        accountDao.createAccount(accountVo);
    }
}

