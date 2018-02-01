package dev.paie.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan({"dev.paie.service", "dev.paie.util"})
@Import({DataSourceMySQLConfig.class, JpaConfig.class})
public class ServicesConfig {
	
}