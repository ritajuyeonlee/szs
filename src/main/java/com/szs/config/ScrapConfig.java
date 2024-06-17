package com.szs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScrapConfig {

    public static String url;
    public static String key;
    public static Integer timeOut;

    public ScrapConfig(
            @Value("${scrap.url}") String url,
            @Value("${scrap.key}") String key,
            @Value("${scrap.timeOut}") Integer timeOut
    ) {
        ScrapConfig.url = url;
        ScrapConfig.key = key;
        ScrapConfig.timeOut = timeOut;
    }


}
