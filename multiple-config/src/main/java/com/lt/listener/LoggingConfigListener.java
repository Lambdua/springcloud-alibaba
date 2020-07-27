package com.lt.listener;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.spring.util.parse.DefaultYamlConfigParse;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * @author liangtao
 * @Date 2020/7/27
 **/
@Component
public class LoggingConfigListener {
    /**
     * 日志配置项的前缀
     */
    private static final String LOGGER_TAG = "logging.level.";

    @Resource
    private LoggingSystem loggingSystem;

    @NacosConfigListener(dataId = "${nacos.config.data-id}", type = ConfigType.YAML, timeout = 5000)
    public void onChange(String newLog){
        Properties parse = new DefaultYamlConfigParse().parse(newLog);
        String levvel = parse.keySet().stream().map(key -> String.valueOf(key)).filter(key -> key.startsWith(LOGGER_TAG)).findFirst().orElse(null);
        if (levvel!=null){
            // 获得日志级别
            String strLevel = parse.getProperty(levvel, "info");
            LogLevel level = LogLevel.valueOf(strLevel.toUpperCase());
            // 设置日志级别到 LoggingSystem 中
            loggingSystem.setLogLevel(levvel.replace(LOGGER_TAG, ""), level);
        }
    }

}
