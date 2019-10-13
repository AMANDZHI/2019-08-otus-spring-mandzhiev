package amandzhi.springjdbc;

import amandzhi.springjdbc.repository.AuthorRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringjdbcApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(SpringjdbcApplication.class, args);
        AuthorRepositoryImpl bean = context.getBean(AuthorRepositoryImpl.class);
        System.out.println(bean.findAll().size());
    }
}