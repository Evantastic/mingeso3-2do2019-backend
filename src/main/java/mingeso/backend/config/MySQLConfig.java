package mingeso.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages =  "mingeso.backend.rest.mysql")
public class MySQLConfig {
}
