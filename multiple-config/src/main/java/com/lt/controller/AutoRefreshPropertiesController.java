package com.lt.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.lt.properties.AutoReadProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liangtao
 * @Date 2020/7/27
 **/
@RestController
@RequestMapping("/demo")
@Slf4j
public class AutoRefreshPropertiesController {
    /**
     * 这里为了实现自动刷新配置的功能，我们也无法使用 @Value 注解，而是使用 @NacosValue 替代。
     * 同时，设置其 autoRefreshed 属性为 true。
     */
    @NacosValue(value = "${test}", autoRefreshed = true)
    private String test;

    @GetMapping("/test")
    public String test() {
        return test;
    }


    @Autowired
    AutoReadProperties autoReadProperties;
    @GetMapping("/testByProperties")
    public String  testByProperties(){
        return autoReadProperties.getTest();
    }


    /**
     * 监听器更新检测
     */
    @GetMapping("/logger")
    public void logger() {
        log.debug("[logger][测试一下]");
    }
}
