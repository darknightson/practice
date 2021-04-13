package com.practice.account.controller;

import com.practice.account.service.AccountService;
import com.practice.account.vo.AccountVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public String createUserForm() {
        return "user/login/register";
    }

    @PostMapping("/users")
    public String createUser(AccountVo accountVo){
        accountVo.setPassword(passwordEncoder.encode(accountVo.getPassword()));
        accountService.createUser(accountVo);
        return "redirect:/";
    }
}
