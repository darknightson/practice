package com.practice.account.controller;

import com.practice.account.service.AccountService;
import com.practice.account.vo.AccountVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/createUser")
    public void createUser(AccountVo accountVo) {
        accountService.createUser(accountVo);
    }
}
