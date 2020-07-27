package com.lt;

import com.lt.properties.OrderProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

/**
 * @author liangtao
 * @Date 2020/7/27
 **/
@SpringBootApplication
public class MultipleConfigApplication {
    public static void main(String[] args){
        SpringApplication.run(MultipleConfigApplication.class,args);
    }


    @Component
    public class OrderPropertiesCommandLineRunner implements CommandLineRunner {
        Logger log = LoggerFactory.getLogger(getClass());
        @Autowired
        OrderProperties orderProperties;

        @Override
        public void run(String... args) throws Exception {
            log.info("订单支付超时时长"+orderProperties.getPayTimeoutSeconds());
            log.info("订单创建频率"+orderProperties.getCreateFrequencySeconds());
        }
    }


    @Component
    public class ValueCommandLineRunner implements CommandLineRunner {
        Logger log =LoggerFactory.getLogger(getClass());

        //        @NacosValue(value = "${order.pay-timeout-seconds}")
        @Value(value = "${order.pay-timeout-seconds}")
        private Integer payTimeoutSeconds;

        //        @NacosValue(value = "${order.create-frequency-seconds}")
        @Value(value = "${order.create-frequency-seconds}")
        private Integer createFrequencySeconds;

        @Override
        public void run(String... args) {
            log.info("payTimeoutSeconds:" + payTimeoutSeconds);
            log.info("createFrequencySeconds:" + createFrequencySeconds);
        }
    }
}
