package com.lt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liangtao
 * @Date 2020/7/27
 **/
@RestController
@RequestMapping("/provider")
public class ProviderController {

    @GetMapping("echo")
    public String provider(){
        return  "echo";
    }
}
