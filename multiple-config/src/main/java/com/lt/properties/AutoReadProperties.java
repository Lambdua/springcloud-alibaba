package com.lt.properties;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author liangtao
 * @Date 2020/7/27
 * 要使用自动刷新配置必须使用@NacosConfigurationProperties
 * 这里有一点要注意，nacos.config.auto-refresh 配置项开启的是全局的，必须为 true 时，才能使用自动刷新配置的功能。
 * 同时，每个 @NacosConfigurationProperties 或 @NacosValue 注解，也需要设置 autoRefreshed 属性为 true 时，对应的配置项才会自动刷新。
 **/
@Component
@Data
@NacosConfigurationProperties(autoRefreshed = true,prefix = "",groupId = "${nacos.config.group}",dataId = "${nacos.config.data-id}",type = ConfigType.YAML)
public class AutoReadProperties {

    /**
     * 测试自动刷新属性
     */
    private String test;
}
