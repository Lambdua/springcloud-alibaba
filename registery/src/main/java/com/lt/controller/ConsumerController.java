package com.lt.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author liangtao
 * @Date 2020/7/27
 **/
@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @NacosInjected
    NamingService namingService;
    @Value("${spring.application.name}")
    String serviceName;

    RestTemplate restTemplate=new RestTemplate();

    @GetMapping("/consumer")
    public String consumer() throws NacosException {
        Instance instance = namingService.selectOneHealthyInstance(serviceName);

        String respongse = restTemplate.getForObject("http://" + instance.toInetAddr() + "/provider/echo",
                String.class);
        return respongse;
    }
}
