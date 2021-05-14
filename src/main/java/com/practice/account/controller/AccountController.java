package com.practice.account.controller;

/**
 * @author  : anthony.son
 * @since   : 2021. 05
 * @version : 1.0
 */

import com.practice.account.service.AccountService;
import com.practice.account.vo.AccountVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/account")
    public String createAccountForm() {
        return "user/login/register";
    }

    @PostMapping("/account")
    public String createAccount(AccountVo accountVo) {
        accountVo.setPassword(passwordEncoder.encode(accountVo.getPassword()));
        accountService.createAccount(accountVo);
        return "redirect:/";
    }

    @GetMapping("/denied")
    public String accessDenied(@RequestParam(value = "exception", required = false) String exception, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountVo accountVo = (AccountVo) authentication.getPrincipal();
        model.addAttribute("userId", accountVo.getUserId());
        model.addAttribute("exception", exception);

        return "user/login/denied";
    }
}