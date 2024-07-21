package com.example.reportgenerator.config;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {
	
    @Autowired
    private SparkProperties sparkProperties;

    @Bean
    public SparkSession sparkSession() {
        return SparkSession.builder()
            .appName(sparkProperties.getAppName())
            .master(sparkProperties.getMaster())
            .getOrCreate();

    }
}
