package com.practice.admin;

/**
 * @author  : anthony.son
 * @since   : 2021. 05
 * @version : 1.0
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConfigController {

    @GetMapping("/config")
    public String config(){
        return "admin/config";
    }
}
