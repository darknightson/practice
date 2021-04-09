package com.practice.account.vo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private String userId;
    private String password;
    private String username;
}
