package amandzhi.springBoot;

import amandzhi.springBoot.config.AppConfig;
import amandzhi.springBoot.executor.TestingApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AppConfig.class)
public class Application {

	public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        TestingApp console = context.getBean(TestingApp.class);
          console.start();
    }
}