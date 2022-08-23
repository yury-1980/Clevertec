package ru.clevertec;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("ru.clevertec")
@PropertySource("classpath:connectionDB.properties")
public class SpringConfig {
}
