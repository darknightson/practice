package com.practice.account.service;

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
    public void createUser(AccountVo accountVo) {
        accountDao.createUser(accountVo);
    }
}
