package com.lt.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liangtao
 * @Date 2020/7/21
 **/
@Component
@ConfigurationProperties(prefix = "order")
//开启了预加载配置项，故不需要在使用nacos的配置注解，解耦合
//@NacosConfigurationProperties(prefix = "order",groupId = "${nacos.config.group}",dataId = "${nacos.config.data-id}",type = ConfigType.YAML)
@Data
public class OrderProperties {
    /**
     * 订单支付超时时长，单位：秒。
     */
   private Integer payTimeoutSeconds;

    /**
     * 订单创建频率，单位：秒
     */
   private Integer createFrequencySeconds;
}
