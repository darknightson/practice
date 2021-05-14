package com.practice.account.vo;

/**
 * @author  : anthony.son
 * @since   : 2021. 05
 * @version : 1.0
 */

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountVo {

    private String userId;
    private String username;
    private String password;
    private String email;
    private String age;
    private String role;
}
