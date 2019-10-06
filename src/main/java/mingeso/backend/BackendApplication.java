package mingeso.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "mingeso.backend.rest.mongo")
@EnableJpaRepositories(basePackages =  "mingeso.backend.rest.mysql")
@SpringBootApplication
public class BackendApplication extends SpringBootServletInitializer{

  public static void main(String[] args) {
    SpringApplication.run(BackendApplication.class, args);
  }

}
