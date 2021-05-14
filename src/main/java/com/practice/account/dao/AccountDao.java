package com.practice.account.dao;

/**
 * @author  : anthony.son
 * @since   : 2021. 05
 * @version : 1.0
 */

import com.practice.account.vo.AccountVo;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao {
    void createAccount(AccountVo accountVo);
    AccountVo selectAccountId(String userId);
}
