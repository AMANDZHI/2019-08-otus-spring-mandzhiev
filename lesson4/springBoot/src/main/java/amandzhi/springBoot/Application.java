package amandzhi.springBoot;

import amandzhi.springBoot.executor.TestingApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        TestingApp console = context.getBean(TestingApp.class);
        console.start();
    }
}