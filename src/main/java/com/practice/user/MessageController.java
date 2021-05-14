package com.practice.user;

/**
 * @author  : anthony.son
 * @since   : 2021. 05
 * @version : 1.0
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageController {

    @GetMapping(value="/messages")
    public String mypage() throws Exception {

        return "user/messages";
    }
}
