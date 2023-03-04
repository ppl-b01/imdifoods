package com.imdifoods.imdifoodswebcommerce.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OauthSecurityController {
    @GetMapping("/")
    public String getRequest(Principal user) {
        return "welcome";
    }
}