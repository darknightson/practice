package com.practice.account.dao;

import com.practice.account.vo.AccountVo;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao {

    void createUser(AccountVo accountVo);

    AccountVo selectUserId(String userId);
}
