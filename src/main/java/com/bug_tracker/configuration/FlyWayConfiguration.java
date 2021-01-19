package com.bug_tracker.configuration;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlyWayConfiguration {
    
        @Autowired
        DataSource dataSource;
        
        
        @Bean
        public FlywayMigrationStrategy flywayMigrationStrategy() {
            return flyway -> {
//                flyway.clean();
//                flyway.migrate();
            };
        }
}
