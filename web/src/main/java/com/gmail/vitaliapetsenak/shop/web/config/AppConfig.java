package com.gmail.vitaliapetsenak.shop.web.config;

import com.gmail.vitaliapetsenak.shop.web.security.SecurityConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.gmail.vitaliapetsenak")
@PropertySource(value = {
        "classpath:database.properties",
        "classpath:news_file.properties",
        "classpath:messages.properties"
})
@Import({
        HibernateConfig.class,
        WebConfig.class,
        SecurityConfiguration.class
})
public class AppConfig {
}
