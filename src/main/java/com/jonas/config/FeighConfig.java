package com.jonas.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2019/01/14
 */
//@Configuration
public class FeighConfig {

    @Bean
    public Logger.Level feignLevel() {
        return Logger.Level.FULL;
    }
}
