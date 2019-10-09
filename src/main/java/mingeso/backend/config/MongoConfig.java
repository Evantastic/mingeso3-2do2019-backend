package mingeso.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "mingeso.backend.rest.mongo")
public class MongoConfig {
}
