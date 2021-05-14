package com.practice;

/**
 * @author  : anthony.son
 * @since   : 2021. 05
 * @version : 1.0
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value="/")
    public String home() throws Exception {
        return "home";
    }
}
